package com.usermange.otpsservice.publisher;

import com.commons.data.entity.Otps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Publisher {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void createProducer( String topic,  Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(object);
        kafkaTemplate.send(topic, data);
    }

    public void createProducerV2( String topic,  Otps object) throws JsonProcessingException {
        object.setCreatedat(null);
        object.setExpiresat(null);
        object.setConfirmedat(null);
        object.setCreateddate(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(object);
        kafkaTemplate.send(topic, data);

    }
}
