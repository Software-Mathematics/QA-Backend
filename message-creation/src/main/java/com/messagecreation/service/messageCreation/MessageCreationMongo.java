package com.messagecreation.service.messageCreation;

import com.messagecreation.dto.Payload;
import com.messagecreation.entity.MessageTableMongo;
import com.messagecreation.entity.TemplateTableMongo;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface MessageCreationMongo {
    MessageTableMongo createMessage(TemplateTableMongo templateTable, Payload payload) throws IOException, TemplateException;
}
