package com.messagecreation.controller;

import com.messagecreation.dto.Payload;
import com.messagecreation.entity.MessageTableMongo;
import com.messagecreation.service.MessageCreationServiceV2;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/message-creation/v3")
public class MessageCreationControllerV3 {

    @Autowired
    private MessageCreationServiceV2 service;

    @PostMapping("/createMessage")
    public MessageTableMongo createMessage(@RequestBody Payload payload) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, TemplateException {
        return service.createMessage(payload);
    }
}
