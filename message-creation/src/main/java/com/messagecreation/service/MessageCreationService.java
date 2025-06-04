//package com.messagecreation.service;
//
//import com.messagecreation.dto.Payload;
//import com.messagecreation.entity.MessageTable;
//import com.messagecreation.entity.TemplateTable;
//import com.messagecreation.repo.MessageTableRepo;
//import com.messagecreation.repo.TemplateTableRepo;
//import com.messagecreation.service.messageCreation.MessageCreation;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class MessageCreationService {
//    @Autowired
//    MessageTableRepo messageTableRepo ;
//    @Autowired
//    TemplateTableRepo templateTableRepo;
//
//    public MessageTable createMessage(Payload payload) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, TemplateException {
//        TemplateTable templateTable = templateTableRepo.findByMsgTypeAndMsgCode(payload.getMsgType(), payload.getMsgCode());
//        Class cls = Class.forName(templateTable.getClassName());
//        MessageCreation messageCreation = (MessageCreation) cls.newInstance();
//        MessageTable messageTable = messageCreation.createMessage(templateTable, payload);
//        messageTableRepo.save(messageTable);
//        return messageTable;
//    }
//}
