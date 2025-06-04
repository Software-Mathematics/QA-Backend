//package com.messagecreation.service.messageCreation;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.messagecreation.dto.Payload;
//import com.messagecreation.entity.MessageTable;
//import com.messagecreation.entity.TemplateTable;
//import com.messagecreation.utility.MessageCreationAdapter;
//import freemarker.template.TemplateException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class EmailMessageCreation implements MessageCreation {
//
//
//
//
//    MessageCreationAdapter messageCreationAdapter = new MessageCreationAdapter();
//
//
//
//    @Override
//    public MessageTable createMessage(TemplateTable templateTable, Payload payload) throws IOException, TemplateException {
//        JSONObject jsonObject = new JSONObject(payload.getModelMap());
//        Map<String,Object> model = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
//        String body=new String();
//        String subject=new String();
//
//        if (templateTable.getBodyTemplateFormat().equalsIgnoreCase("ftl")){
//            body = messageCreationAdapter.createTemplate(templateTable.getBodyTemplatePath(), templateTable.getBodyTemplateName(), model);
//        }else {
//
//        }
//        if (templateTable.getSubTemplateFormat().equalsIgnoreCase("ftl")){
//            subject = messageCreationAdapter.createTemplate(templateTable.getSubTemplatePath(), templateTable.getSubTemplateName(), model);
//        }else {
//
//        }
//        MessageTable messageTable = new MessageTable(
//                payload.getMsgType(),
//                payload.getCorrelId(),
//                payload.getResourceCode(),
//                payload.getTo(),
//                payload.getCc(),
//                payload.getBcc(),
//                subject,
//                body
//        );
//        messageTable.setCreatedBy(payload.getTo());
//        messageTable.setCreatedDate(new Date());
//        messageTable.setStatus("SENDING");
//
//        return messageTable;
//    }
//}
