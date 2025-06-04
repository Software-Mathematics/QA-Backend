package com.messagecreation.entity;

import com.messagecreation.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "MessageTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTableMongo extends BaseDTO {
    @Id
    private String id;
    private String type;
    private String correlId;
    private String resourceCode;
    private String to;
    private String[] toArr;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String body;
    private String tanentid;


    public MessageTableMongo(String type, String correlId, String resourceCode, String to,String[] toArr, String[] cc, String[] bcc, String subject, String body, String tanentid) {
        this.type = type;
        this.correlId = correlId;
        this.resourceCode = resourceCode;
        this.to = to;
        this.toArr = toArr;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.tanentid=tanentid;

    }
}

