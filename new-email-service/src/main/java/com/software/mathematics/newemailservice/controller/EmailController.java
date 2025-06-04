package com.software.mathematics.newemailservice.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.software.mathematics.newemailservice.dto.MessageTableMongo;
import com.software.mathematics.newemailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email/v1/")
public class EmailController {

    @Autowired
    private EmailService service;

    @KafkaListener(topics = "${topic.one}", groupId = "email_1")
    public void consume(String message){
        try {
            System.out.println("message = " + message);
            Gson gson = new Gson();
            MessageTableMongo messageTable = gson.fromJson(message, new TypeToken<MessageTableMongo>() {
            }.getType());
            service.sendMail(
                    messageTable.getTo(),
                    messageTable.getToArr(),
                    messageTable.getCc(),
                    messageTable.getBcc(),
                    messageTable.getSubject(),
                    messageTable.getBody()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("create")
    public void createMail(@RequestBody MessageTableMongo messageTable){
        try {
            System.out.println("messageTable = " + messageTable);
            service.sendMail(
                    messageTable.getTo(),
                    messageTable.getToArr(),
                    messageTable.getCc(),
                    messageTable.getBcc(),
                    messageTable.getSubject(),
                    messageTable.getBody()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
