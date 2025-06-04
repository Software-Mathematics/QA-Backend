package com.messagecreation.service;

import com.commons.data.dao.commonDao.BaseDaoImpl;
import com.commons.data.entity.Resource;
import com.messagecreation.dto.Payload;
import com.messagecreation.entity.MessageTableMongo;
import com.messagecreation.entity.TemplateTableMongo;
import com.messagecreation.publisher.Publisher;
import com.messagecreation.repo.MessageTableRepoMongo;
import com.messagecreation.repo.TemplateTableRepoMongo;
import com.messagecreation.service.messageCreation.MessageCreationMongo;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageCreationServiceV2 {
    @Autowired
    MessageTableRepoMongo messageTableRepo ;
    @Autowired
    TemplateTableRepoMongo templateTableRepoMongo;

    @Autowired
    private Publisher publisher;

    @Value("${topic.email}")
    private String emailTopic;


    @Value("${topic.telegram}")
    private String telegramTopic;
    @Value("${topic.sms}")
    private String smsTopic;
    @Value("${topic.notification}")
    private String notificationTopic;

    @Autowired
    private BaseDaoImpl baseDao;

    public MessageTableMongo createMessage(Payload payload) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, TemplateException {
        TemplateTableMongo templateTableMongo = templateTableRepoMongo.findByMsgTypeAndMsgCode(payload.getMsgType(), payload.getMsgCode());
        Class cls = Class.forName(templateTableMongo.getClassName());
        MessageCreationMongo messageCreationMongo = (MessageCreationMongo) cls.newInstance();
        MessageTableMongo messageTableMongo = messageCreationMongo.createMessage(templateTableMongo, payload);
        messageTableRepo.save(messageTableMongo);
        Map<String, Object> map = new HashMap<>();
        map.put("resourcecode", payload.getResourceCode());
        map.put("type", "TOPIC");
        String topic = "";
            if (payload.getMsgType().equalsIgnoreCase("SMS")) {
                topic = smsTopic;
                publisher.createProducer(smsTopic, messageTableMongo);
//                map.put("subtype", "SMS");
//                List<Resource> resources = baseDao.get(map, new Resource());
//                if (resources.size() > 0) {
//                    Resource resource = resources.get(0);
////                    publisher.createProducer(resource.getResourcecode() + "_" + resource.getDescription(), messageTableMongo);
//                    publisher.createProducer(smsTopic, messageTableMongo);
//                }
            } else if (payload.getMsgType().equalsIgnoreCase("EMAIL")) {
//                map.put("subtype", "EMAIL");
//                List<Resource> resources = baseDao.get(map, new Resource());
//                if (resources.size() > 0) {
//                    Resource resource = resources.get(0);
////                    publisher.createProducer(resource.getResourcecode() + "_" + resource.getDescription(), messageTableMongo);
//                    publisher.createProducer(emailTopic, messageTableMongo);
//                }
                publisher.createProducer(emailTopic, messageTableMongo);
                topic = emailTopic;
            }
            else if (payload.getMsgType().equalsIgnoreCase("TELEGRAM")) {
                publisher.createProducer(telegramTopic, messageTableMongo);
                topic = telegramTopic;
            }
            else if (payload.getMsgType().equalsIgnoreCase("NOTIFICATION")) {
                publisher.createProducer(notificationTopic, messageTableMongo);
                topic = notificationTopic;
            }

        System.out.println("Object send to {" + topic + "}");
        return messageTableMongo;
    }
}
