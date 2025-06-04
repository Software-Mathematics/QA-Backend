package com.software.mathematics.newemailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTableMongo {

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

}
