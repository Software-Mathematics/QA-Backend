package com.QA.qaintegrationservice.consumer;

import com.QA.qaintegrationservice.service.QATransactionServiceImpl;
import com.commons.data.multitenancy.context.TenantContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Consumer {

    @Autowired
    private QATransactionServiceImpl service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${spring.kafka.topic.consumer.name}", groupId = "${spring.kafka.topic.consumer.name.group-id}")
    public void consumeMessage(String message) {
        try {
//            System.out.println(message);

            Map<String, Object> kafkaMessage = objectMapper.readValue(message, Map.class);

            TenantContext.setTenantId((String) kafkaMessage.get("tanentid"));
            service.update(kafkaMessage);

            System.out.println("Data Updated");
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
