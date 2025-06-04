package com.userm.login.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userm.login.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Consumer {

    @Autowired
    private LoginServiceImpl service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.topic.technician}", groupId = "${spring.kafka.consumer.technician-group-id}")
    public void consumeMessage(String message) {
        try {
            System.out.println(message);

            Map<String, Object> kafkaMessage = objectMapper.readValue(message, Map.class);

            service.updateV3(kafkaMessage);

            System.out.println("Data Updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
