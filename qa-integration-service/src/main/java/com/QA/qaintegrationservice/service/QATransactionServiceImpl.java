package com.QA.qaintegrationservice.service;

import com.QA.qaintegrationservice.config.CSPNonceFilter;
import com.QA.qaintegrationservice.publisher.Publisher;
import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.dao.commonDao.BaseDaoImplV2;
import com.commons.data.dao.daoImplementation.DataSetDao;
import com.commons.data.dao.daoImplementation.ProjectScenarioAssociationDao;
import com.commons.data.dao.daoImplementation.QATransactionDao;
import com.commons.data.dao.dto.FilterByPage;
import com.commons.data.entity.*;
import com.commons.data.multitenancy.context.TenantContext;
import com.commons.util.adapter.BaseAdapter;
import com.commons.util.model.dto.QATransactionDTO;
import com.commons.util.model.dto.BaseDtoI;
import com.commons.util.model.error.AppException;
import com.commons.util.model.error.ErrorRepository;
import com.commons.util.sequenceGenerator.Db_sequenceDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.*;

@Service
public class QATransactionServiceImpl  {
    @Autowired
    private QATransactionDao dao;

    @Autowired
    private DataSetDao dataSetDao;

    @Autowired
    private ProjectScenarioAssociationDao projectScenarioAssociationDao;

    @Autowired
    private BaseDaoImpl baseDao;
    @Autowired
    private BaseDaoImplV2 baseDaoImplV2;

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    @Autowired
    private BaseAdapter baseAdapter;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private Db_sequenceDao dbseq;

    @Value("${status.synced}")
    private String syncedStatus;

    @Value("${status.not-synced}")
    private String notSyncedStatus;

    @Value("${spring.kafka.topic.push.name}")
    private String pushTopic;

    @Autowired
    private Publisher publisher;

    public BaseDtoI createPayload(Map<String, Object> map) throws AppException {
        try {
            String transactionId = baseDao.generateUUID();
            QATransaction transaction = new QATransaction();
            transaction.setTransaction_id(transactionId);
            transaction.setUpdate_date(new Date().toString());
            transaction.setTanentid(TenantContext.getTenantId());
            transaction.setCreatedby((String) map.get("createdby"));

            if (map.containsKey("scenario_code")) {
                String scenarioCodes = (String) map.get("scenario_code");
                List<String> scenarioCodeList = Arrays.asList(scenarioCodes.split(","));
                Map<String, Object> getMap = new HashMap<>();
                getMap.put("scenario.code", scenarioCodeList);
                List<DataSet> dataSets = baseDao.getV2(getMap, new DataSet());
                transaction.setType("SCENARIO");
                transaction.setValue(scenarioCodes);
                transaction.setData_list(dataSets);
            }

            else if (map.containsKey("project_code")) {
                String projectCodes = (String) map.get("project_code");
                List<String> projectCodeList = Arrays.asList(projectCodes.split(","));

                List<Project> childProjectList = baseDaoImplV2.findByStatusAndProjecthirarichalcodeInOrLike("ACTIVE", projectCodeList);
                projectCodeList = childProjectList.stream().map(Project::getCode).collect(Collectors.toList());



                //get-project-association==
                Map<String, Object> getMap = new HashMap<>();
                getMap.put("project.code", projectCodeList);
                List<ProjectScenarioAssociation> associations = baseDao.getV2(getMap, new ProjectScenarioAssociation());

                //filter-scenario-code-from-association===
                List<String> scenarioCodeList = associations.stream()
                        .filter(assoc -> assoc.getScenario() != null)
                        .flatMap(assoc -> assoc.getScenario().stream().map(Scenario::getCode))
                        .collect(Collectors.toList());

                //get-datasets-by-scenario-codes===
                getMap.remove("project.code");
                getMap.put("scenario.code", scenarioCodeList);
                List<DataSet> dataSets = baseDao.getV2(getMap, new DataSet());
                transaction.setType("PROJECT");
                transaction.setValue(projectCodes);
                transaction.setData_list(dataSets);
            }
            transaction.setId(baseDao.generateSequence(QATransaction.SEQUENCE_NAME));
            String status = "";
            if (transaction.getData_list() == null || transaction.getData_list().isEmpty()){
                status = "OPEN";
                transaction.setReport(
                        "<div style=\"\n" +
                        "    padding: 1rem; \n" +
                        "    margin: 1rem 0; \n" +
                        "    text-align: center; \n" +
                        "    color: #1e3a8a; \n" +
                        "    background-color: #bfdbfe; \n" +
                        "    border: 1px solid #3b82f6; \n" +
                        "    border-radius: 0.375rem;\">\n" +
                        "    Please add datasets for this scenario first before proceeding!!\n" +
                        "</div>"
                );
            }else {
                status = "RUNNING";
            }
            transaction = dao.save(transaction);
            updateRecords(transaction.getType(), transaction.getValue(), transactionId, status);
            sendToKafka(transaction);
            transaction.setData_list(null);
            return (BaseDtoI) baseAdapter.entityToDTO(transaction, new QATransactionDTO());
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public void updateRecords(String type, String value, String transactionId, String status) {
        Map<String, Object> criteriaMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("transaction_id", transactionId);
        dataMap.put("recstatus", status);

        if ("PROJECT".equalsIgnoreCase(type)) {
            criteriaMap.put("code", value);
            baseDao.update(criteriaMap, dataMap, new Project());
        } else if ("SCENARIO".equalsIgnoreCase(type)) {
            criteriaMap.put("code", value);
            baseDao.update(criteriaMap, dataMap, new Scenario());
        }
    }

    public void sendToKafka(QATransaction transaction) throws JsonProcessingException {
        publisher.createProducer(pushTopic, transaction);
    }


    public void update(Map<String, Object> kafkaMessage) {
        try {
            String transactionId = (String) kafkaMessage.get("transaction_id");
            String report = (String) kafkaMessage.get("report");
            String type = (String) kafkaMessage.get("type");
            String recstatus = (String) kafkaMessage.get("recstatus");
            String progress = (String) kafkaMessage.get("progress");
            String processed_datasets = (String) kafkaMessage.get("processed_datasets");
            String total_datasets = (String) kafkaMessage.get("total_datasets");
            String value = (String) kafkaMessage.get("value");
            String tanentid = (String) kafkaMessage.get("tanentid");

            System.out.println("tranaction_id: " +  transactionId + " ,type: " + type);
            if (transactionId == null || type == null) {
                System.out.println("Invalid message: transaction_id or type is missing.");
                return;
            }

            // Update QATransaction Collection
            Map<String, Object> criteriaMap = new HashMap<>();
            criteriaMap.put("transaction_id", transactionId);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("report", report);
            dataMap.put("update_date", new Date().toString());
            dataMap.put("recstatus", recstatus);
            dataMap.put("progress", progress);
            dataMap.put("processed_datasets", processed_datasets);
            dataMap.put("total_datasets", total_datasets);

            baseDao.update(criteriaMap, dataMap, new QATransaction());
            System.out.println("QATransaction updated successfully.");

            // Update Project or Scenario based on type
            Map<String, Object> map = new HashMap<>();
            if ("PROJECT".equalsIgnoreCase(type)) {
                map.put("modifieddate", new Date());
                map.put("laststatus", recstatus);
                map.put("processed_datasets", processed_datasets);
                map.put("total_datasets", total_datasets);

                if ("SUCCESS".equalsIgnoreCase(recstatus) || "FAILURE".equalsIgnoreCase(recstatus)) {
                    map.put("recstatus", "OPEN");
                    map.put("progress", "0");

                } else {
                    map.put("progress", progress);
                }
                baseDao.update(criteriaMap, map, new Project());
                System.out.println("Project updated successfully.");
            } else if ("SCENARIO".equalsIgnoreCase(type)) {
                map.put("recstatus", "OPEN");
                map.put("modifieddate", new Date());
                baseDao.update(criteriaMap, map, new Scenario());
                System.out.println("Scenario updated successfully.");
            }
            // Send notification payload to Kafka
            sendNotification(transactionId);
            if ("SUCCESS".equalsIgnoreCase(recstatus) || "FAILURE".equalsIgnoreCase(recstatus)) {
                sendEmailNotification(transactionId, recstatus, tanentid, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error processing Kafka message: " + e.getMessage());
        }
    }


    public List<BaseDtoI> get(Map<String, Object> map) throws AppException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            return (List) baseAdapter.entityToDTO(baseDao.getV2(map, new QATransaction()), new QATransactionDTO());
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public Page getByPage(FilterByPage filter) throws AppException {
        try {
            if (filter != null && filter.getPolygonCoordinates() != null && !baseDaoImplV2.isValidPolygon(filter.getPolygonCoordinates())){
                throw new AppException("Invalid Polygon !!");
            }
//            return baseDaoImplV2.getByPage(filter, new QATransaction());
            Page<QATransaction> pageResult = baseDaoImplV2.getByPage(filter, new QATransaction());
            Page<QATransaction> modifiedPage = pageResult.map(transaction -> {
                transaction.setData_list(null);
                return transaction;
            });

            return modifiedPage;
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    public void sendNotification(String transactionId) {
        try {
            Map<String, Object> getMap = new HashMap<>();
            getMap.put("transaction_id", transactionId);
            List<QATransaction> qaTransactionList = baseDao.getV2(getMap, new QATransaction());

            if (qaTransactionList == null || qaTransactionList.isEmpty()) {
                System.out.println("No transaction found for ID: " + transactionId);
                return;
            }
            QATransaction transaction = qaTransactionList.get(qaTransactionList.size()-1);

            Map<String, Object> objectData = new HashMap<>();
            objectData.put("transaction_id", transaction.getTransaction_id());
            objectData.put("type", transaction.getType());
            objectData.put("value", transaction.getValue());
            objectData.put("update_date", transaction.getUpdate_date());
            objectData.put("tanentid", transaction.getTanentid());
            objectData.put("recstatus", transaction.getRecstatus());
            objectData.put("progress", transaction.getProgress());
            objectData.put("processed_datasets", transaction.getProcessed_datasets());
            objectData.put("total_datasets", transaction.getTotal_datasets());

            Map<String, Object> payload = new HashMap<>();
            payload.put("to", transaction.getCreatedby());
            payload.put("status", "COMPLETED");
            payload.put("object", objectData);

            publisher.createProducer("IN_APP_NOTIFICATION", payload);
            System.out.println("Notification sent to Kafka: " + payload);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending notification: " + e.getMessage());
        }
    }

    public BaseDtoI createPayloadV2(Map<String, Object> map) throws AppException {
        try {
            String transactionId = baseDao.generateUUID();
            QATransaction transaction = new QATransaction();
            transaction.setTransaction_id(transactionId);
            transaction.setUpdate_date(new Date().toString());
            transaction.setTanentid(TenantContext.getTenantId());
            transaction.setCreatedby((String) map.get("createdby"));
            String kafkaTopic = "";

            if (map.containsKey("scenario_code")) {
                String scenarioCodes = (String) map.get("scenario_code");
                List<String> scenarioCodeList = Arrays.asList(scenarioCodes.split(","));
                Map<String, Object> getMap = new HashMap<>();
                getMap.put("scenario.code", scenarioCodeList);
                List<DataSet> dataSets = baseDao.getV2(getMap, new DataSet());
                transaction.setType("SCENARIO");
                transaction.setValue(scenarioCodes);
                transaction.setData_list(dataSets);
                kafkaTopic = "QATransaction_API_PAYLOAD";
            }
            else if (map.containsKey("project_code")) {

                String projectCodes = (String) map.get("project_code");
                List<String> projectCodeList = Arrays.asList(projectCodes.split(","));
                List<Project> childProjectList = baseDaoImplV2.findByStatusAndProjecthirarichalcodeInOrLike("ACTIVE", projectCodeList);
                projectCodeList = childProjectList.stream().map(Project::getCode).collect(Collectors.toList());

                Map<String, Object> getMap = new HashMap<>();
                getMap.put("project.code", projectCodeList);
                List<ProjectScenarioAssociation> associations = baseDao.getV2(getMap, new ProjectScenarioAssociation());

                String type = associations.stream()
                        .filter(assoc -> assoc.getType() != null)
                        .map(ProjectScenarioAssociation::getType)
                        .findFirst()
                        .orElse(null);

                if ("API".equalsIgnoreCase(type)) {
                    // Get scenario codes
                    List<String> scenarioCodeList = associations.stream()
                            .filter(assoc -> assoc.getScenario() != null)
                            .flatMap(assoc -> assoc.getScenario().stream().map(Scenario::getCode))
                            .collect(Collectors.toList());

                    // Get datasets
                    getMap.clear();
                    getMap.put("scenario.code", scenarioCodeList);
                    List<DataSet> dataSets = baseDao.getV2(getMap, new DataSet());

                    transaction.setType("PROJECT");
                    transaction.setValue(projectCodes);
                    transaction.setData_list(dataSets);
                    kafkaTopic = "QATransaction_API_PAYLOAD";

                } else if ("FEATURE".equalsIgnoreCase(type)) {
                    // Get feature codes
                    List<String> featureCodeList = associations.stream()
                            .filter(assoc -> assoc.getFeatures() != null)
                            .flatMap(assoc -> assoc.getFeatures().stream().map(FeatureScenario::getCode))
                            .collect(Collectors.toList());

                    // Get feature datasets
                    getMap.clear();
                    getMap.put("feature_code", featureCodeList);
                    List<FeatureDataset> featureDatasets = baseDao.getV2(getMap, new FeatureDataset());

                    transaction.setType("PROJECT");
                    transaction.setValue(projectCodes);
                    transaction.setFeature_data_list(featureDatasets);
                    kafkaTopic = "QATransaction_UI_PAYLOAD";
                } else {
                    return null;
                }
            }
            transaction.setId(baseDao.generateSequence(QATransaction.SEQUENCE_NAME));
            String status = "";

//            boolean isEmpty =
//                    ("FEATURE".equalsIgnoreCase(type) && (transaction.getFeature_data_list() == null || transaction.getFeature_data_list().isEmpty())) ||
//                            ("API".equalsIgnoreCase(type) && (transaction.getData_list() == null || transaction.getData_list().isEmpty()));

            boolean isEmpty =
                    (transaction.getFeature_data_list() == null || transaction.getFeature_data_list().isEmpty()) &&
                            (transaction.getData_list() == null || transaction.getData_list().isEmpty());

            if (isEmpty) {
                status = "OPEN";
                transaction.setReport(
                        "<div style=\"\n" +
                                "    padding: 1rem; \n" +
                                "    margin: 1rem 0; \n" +
                                "    text-align: center; \n" +
                                "    color: #1e3a8a; \n" +
                                "    background-color: #bfdbfe; \n" +
                                "    border: 1px solid #3b82f6; \n" +
                                "    border-radius: 0.375rem;\">\n" +
                                "    Please add datasets for this scenario first before proceeding!!\n" +
                                "</div>"
                );
            } else {
                status = "RUNNING";
            }

            transaction = dao.save(transaction);
            updateRecords(transaction.getType(), transaction.getValue(), transactionId, status);
            sendToKafkaV2(kafkaTopic, transaction);

            transaction.setData_list(null);
            transaction.setFeature_data_list(null);
            return (BaseDtoI) baseAdapter.entityToDTO(transaction, new QATransactionDTO());

        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public void sendToKafkaV2(String kafkaTopic, QATransaction transaction) throws JsonProcessingException {
        publisher.createProducer(kafkaTopic, transaction);
    }

    public void sendEmailNotification(String transactionId, String recstatus, String tanentid, String value) {
        try {
            Map<String, Object> query = new HashMap<>();
            query.put("code", value);

            List<Project> projects = (List<Project>) baseDao.get(query, new Project());

            if (projects.isEmpty()) {
                throw new RuntimeException("No Project record found for transactionId: " + transactionId);
            }
            Project project = projects.get(projects.size()-1);
            String projectName = project.getName();

            Map<String, Object> queryMapV2 = new HashMap<>();
            queryMapV2.put("value", value);

            List<QATransaction> qaTransactions = (List<QATransaction>) baseDao.get(queryMapV2, new QATransaction());

            if (qaTransactions.isEmpty()) {
                throw new RuntimeException("No QATransaction record found for transactionId: " + transactionId);
            }
            QATransaction qaTransaction = qaTransactions.get(qaTransactions.size()-1);
            String createdBy = qaTransaction.getCreatedby();

            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("profileid", createdBy);

            List<Login> logins = (List<Login>) baseDao.get(queryMap, new Login());

            if (logins.isEmpty()) {
                throw new RuntimeException("No Login record found for profileId: " + createdBy);
            }
            Login login = logins.get(logins.size()-1);
            String to = login.getName();
            String emailId = login.getEmailid();

            Map<String, Object> emailPayload = new HashMap<>();
            emailPayload.put("type", "EMAIL");
            emailPayload.put("correlId", "");
            emailPayload.put("msgCode", "EL001");
            emailPayload.put("msgType", "EMAIL");
            emailPayload.put("to", emailId);
            emailPayload.put("cc", new ArrayList<>());
            emailPayload.put("bcc", new ArrayList<>());
            emailPayload.put("resourceCode", "QA");
            emailPayload.put("tanentid", tanentid);

            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("title", "Test Report Status");
            modelMap.put("transactionId", transactionId);
            modelMap.put("projectName", projectName);
            modelMap.put("recstatus", recstatus);
            modelMap.put("tanentid", tanentid);
            modelMap.put("username", to);

            emailPayload.put("modelMap", modelMap);

            publisher.createProducer("DAYCO_MSG_CREATION_MC1", emailPayload);
            System.out.println("Email payload sent to Kafka for transaction ID: " + transactionId);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email notification: " + e.getMessage());
        }
    }

}