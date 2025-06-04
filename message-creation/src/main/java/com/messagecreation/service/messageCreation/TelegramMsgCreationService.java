package com.messagecreation.service.messageCreation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagecreation.dto.Payload;
import com.messagecreation.entity.MessageTableMongo;
import com.messagecreation.entity.TemplateTableMongo;
import com.messagecreation.utility.MessageCreationAdapter;
import freemarker.template.TemplateException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramMsgCreationService implements MessageCreationMongo {

    MessageCreationAdapter messageCreationAdapter = new MessageCreationAdapter();

    @Override
    public MessageTableMongo createMessage(TemplateTableMongo templateTableMongo, Payload payload) throws IOException, TemplateException {
        JSONObject jsonObject = new JSONObject(payload.getModelMap());
        Map<String,Object> model = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
        String body=new String();
        String subject=new String();

        if (templateTableMongo.getBodyTemplateFormat().equalsIgnoreCase("ftl")){
            body = messageCreationAdapter.createTemplate(templateTableMongo.getBodyTemplatePath(), templateTableMongo.getBodyTemplateName(), model);
        }else {

        }
        if (templateTableMongo.getSubTemplateFormat().equalsIgnoreCase("ftl")){
            subject = messageCreationAdapter.createTemplate(templateTableMongo.getSubTemplatePath(), templateTableMongo.getSubTemplateName(), model);
        }else {

        }
        MessageTableMongo messageTableMongo = new MessageTableMongo(
                payload.getMsgType(),
                payload.getCorrelId(),
                payload.getResourceCode(),
                payload.getTo(),
                payload.getToArr(),
                payload.getCc(),
                payload.getBcc(),
                subject,
                body,
                payload.getTanentid()

        );
        messageTableMongo.setCreatedBy(payload.getTo());
        messageTableMongo.setCreatedDate(new Date());
        messageTableMongo.setStatus("SENDING");

        return messageTableMongo;
    }
}

