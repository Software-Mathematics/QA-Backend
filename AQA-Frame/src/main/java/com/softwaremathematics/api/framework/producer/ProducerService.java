package com.softwaremathematics.api.framework.producer;




import com.softwaremathematics.api.framework.model.ScenarioMain;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private static KafkaTemplate<String, ScenarioMain> kafkaTemplate = null;

    public ProducerService(KafkaTemplate<String, ScenarioMain> kafkaTemplate) {
        ProducerService.kafkaTemplate = kafkaTemplate;
    }


    public static void sendMessage(String topic, ScenarioMain model) {

        kafkaTemplate.send(topic, model);

    }


}

