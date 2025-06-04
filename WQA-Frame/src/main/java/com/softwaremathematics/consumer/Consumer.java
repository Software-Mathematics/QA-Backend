package com.softwaremathematics.consumer;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.softwaremathematics.model.ActionDetail;
import com.softwaremathematics.model.RecStatus;
import com.softwaremathematics.model.ScenarioMain;
import com.softwaremathematics.model.UIModel;
import com.softwaremathematics.util.DynamicWebAutomation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Consumer {

    private final List<ScenarioMain> receivedMessages = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private  ExtentReports extent;



    @Value("${kafka.source-topic}")
    private String sourceTopic;


    @Value("${kafka.destination-topic}")
    private String destinationTopic;

    @Value("${kafka.group.id}")
    private String groupId;

    DynamicWebAutomation automation;
    ScenarioMain scenarioMain;

   @KafkaListener(topics = "${kafka.source-topic}", groupId = "${kafka.group.id}", containerFactory = "manualAckListenerContainerFactory")
    public void consumeMessage(String message, Acknowledgment ack) throws IOException, InterruptedException {
       System.out.println("🔹 Received message from Kafka: " + message);

       scenarioMain = new ScenarioMain();
       try {
           scenarioMain = objectMapper.readValue(message, ScenarioMain.class);
           receivedMessages.add(scenarioMain);
           ack.acknowledge();
           extent = null;
           setupExtentReports();
           consumeMessage(scenarioMain);
           generateAndSendReport(scenarioMain);
       }catch (Exception e) {
           e.printStackTrace();
           generateAndSendReport(scenarioMain);
       }

   }

    public void consumeMessage(ScenarioMain scenario) throws IOException, InterruptedException {
        int processedDatasets = 0;
        int totalDatasets = scenario.getFeature_data_list().stream()
                .filter(dataList -> dataList.getDatasets() != null)
                .mapToInt(dataList -> dataList.getDatasets().size())
                .sum();
        scenario.setTotal_datasets(String.valueOf(totalDatasets));
        scenario.setRecstatus(RecStatus.PENDING);
       for (UIModel uiModel : scenario.getFeature_data_list()) {

            ExtentTest test = extent.createTest("Feature: " + uiModel.getFeature_name())
                    .assignCategory("UI API Testing")
                    .assignAuthor("Automation Team");


            try {
            // Iterate through all datasets (key -> string, value -> list of actions)
        for (Map.Entry<String, List<ActionDetail>> datasetEntry : uiModel.getDatasets().entrySet()) {
            String datasetId = datasetEntry.getKey();
            processedDatasets++;
            int progress = (int) ((processedDatasets / (double) totalDatasets) * 100);

            try {
                scenarioMain.setProgress(String.valueOf(progress));
                scenarioMain.setProcessed_datasets(String.valueOf(processedDatasets));
            } catch (Exception e) {
                scenarioMain.setRecstatus(RecStatus.FAILURE);
                scenarioMain.setProgress(String.valueOf("0"));
                scenarioMain.setProcessed_datasets(String.valueOf("0"));
                kafkaTemplate.send(destinationTopic, objectMapper.writeValueAsString(scenarioMain));
            }
            List<ActionDetail> actions = datasetEntry.getValue();


            // Convert each ActionDetail to Map<String, String> for compatibility
            List<Map<String, String>> actionSequence = actions.stream()
                    .map(action -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("srno", String.valueOf(action.getSrno()));
                        map.put("action", action.getAction());
                        map.put("xpath", action.getXpath());
                        map.put("value", action.getValue());
                        map.put("label", action.getLabel());
                        return map;
                    })
                    .collect(Collectors.toList());

            System.out.println("Running dataset: " + actionSequence);
            automation = new DynamicWebAutomation(extent, test, uiModel.getBrowser(), uiModel.getEmail(), uiModel.getPassword(), uiModel.getSubject());

            automation.executeAutomation(actionSequence, uiModel.getFeature_name());
            if (processedDatasets < totalDatasets) {
                kafkaTemplate.send(destinationTopic, objectMapper.writeValueAsString(scenarioMain));
            }
        }
            } catch (Exception e) {
            test.fail("❌ Error while processing feature: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("CLosing the browser");
            automation.closeBrowser();  // Ensure browser closes even if error happens
        }
        }

        System.out.println("flush the report...");
        extent.flush();
//        generateAndSendReport(model);
    }

    private void generateAndSendReport(ScenarioMain scenario) throws IOException {
        String responseTopic = "QATransaction_API_REPORT";
       try {
//        try {
           String htmlContent = new String(Files.readAllBytes(Paths.get("UITestReport.html")), StandardCharsets.UTF_8);
           scenario.setReport(htmlContent);

//        } catch (IOException e) {
//            System.err.println("❌ Failed to read Extent Report: " + e.getMessage());
//        }
           scenario.setRecstatus(RecStatus.SUCCESS);
           scenario.setProgress("100");

           String finalMessage = objectMapper.writeValueAsString(scenario);

           System.out.println("📁 Report will be generated at: " + new File("UITestReport.html").getAbsolutePath());


           kafkaTemplate.send(destinationTopic, finalMessage);
           System.out.println("📢 Published response to Kafka topic: " + responseTopic);

           File file = new File("UITestReport.html");
           if (!file.delete()) {
               System.err.println("⚠️ Failed to delete APIReport.html");
           }
       }catch (Exception e) {
           e.printStackTrace();
           scenario.setReport(getErrorHtml());
           String finalMessage = objectMapper.writeValueAsString(scenario);
           kafkaTemplate.send(destinationTopic, finalMessage);
           System.out.println("📢 Published response to Kafka topic: " + responseTopic);
       }


    }

    private  void setupExtentReports() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File("UITestReport.html"));
            htmlReporter.config().setDocumentTitle("UI Test Report");
            htmlReporter.config().setReportName("UI Message Processing");
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Environment", "Local");
            extent.setSystemInfo("Tester", "Automation Team");
        }

    }


    private String getErrorHtml() {
       return "<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Error Message</title>\n" +
               "    <style>\n" +
               "        .error-message {\n" +
               "            color: #721c24;\n" +
               "            background-color: #f8d7da;\n" +
               "            border: 1px solid #f5c6cb;\n" +
               "            padding: 15px;\n" +
               "            border-radius: 5px;\n" +
               "            margin: 20px;\n" +
               "            font-family: Arial, sans-serif;\n" +
               "        }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "\n" +
               "<div class=\"error-message\">\n" +
               "    <strong>Error:</strong> Something went wrong. Please try again later.\n" +
               "</div>\n" +
               "\n" +
               "</body>\n" +
               "</html>\n";
    }

}
