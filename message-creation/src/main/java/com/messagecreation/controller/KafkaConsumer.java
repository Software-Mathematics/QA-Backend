package com.messagecreation.controller;

import com.commons.data.multitenancy.context.TenantContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.messagecreation.dto.Payload;
import com.messagecreation.service.MessageCreationServiceV2;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class KafkaConsumer {


    @Autowired
    private MessageCreationServiceV2 service;

    @KafkaListener(topics = "${topic.one}", groupId = "template_1")
    public void consume(String message) throws TemplateException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("message = " + message);

        try {
            Gson gson = new Gson();
            Payload object = gson.fromJson(message, new TypeToken<Payload>() {
            }.getType());
            TenantContext.setTenantId(object.getTanentid());
            service.createMessage(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
