package com.softwaremathematics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaremathematics.model.ScenarioMain;
import com.softwaremathematics.model.UIModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
@RequestMapping("/")
public class UIControllerClass {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UIControllerClass(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("create")
    public ResponseEntity<String> save(@RequestBody ScenarioMain uiModel) {
        try {
            String message = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(uiModel);

            // 🔄 Use Spring KafkaTemplate (No need for manual producer)
            kafkaTemplate.send("ui-new11", message);

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

    @GetMapping("api-health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Healthy");
    }
}