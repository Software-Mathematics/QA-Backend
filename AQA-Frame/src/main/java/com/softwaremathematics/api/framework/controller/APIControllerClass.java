package com.softwaremathematics.api.framework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaremathematics.api.framework.model.ScenarioMain;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class APIControllerClass {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public APIControllerClass(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("create/api")
    public ResponseEntity<String> save(@RequestBody ScenarioMain uiModel) {
        try {
            String message = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(uiModel);

            kafkaTemplate.send("api-topic-2", message);

            System.out.println("Message sent: " + message);
            return ResponseEntity.ok("Automation request sent successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Automation failed: " + e.getMessage());
        }
    }

    @PostMapping("/test")
    public ResponseEntity<String> testApi(@RequestBody String payload) {
        System.out.println("Received: " + payload);
        return ResponseEntity.ok("Received");
    }

}