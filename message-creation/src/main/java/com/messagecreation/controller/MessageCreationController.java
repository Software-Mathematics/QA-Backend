//package com.messagecreation.controller;
//
//import com.messagecreation.dto.Payload;
//import com.messagecreation.entity.MessageTable;
//import com.messagecreation.service.MessageCreationService;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/message-creation/v1")
//public class MessageCreationController {
//
//    @Autowired
//    private MessageCreationService service;
//
//    @PostMapping("/createMessage")
//    public MessageTable createMessage(@RequestBody Payload payload) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, TemplateException {
//        return service.createMessage(payload);
//    }
//}
