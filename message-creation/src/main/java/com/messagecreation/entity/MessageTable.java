//package com.messagecreation.entity;
//
//import com.messagecreation.dto.BaseDTO;
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(
//        name = "MessageTable"
//)
//@Data
//public class MessageTable extends BaseDTO {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String type;
//    private String correlId;
//    private String resourceCode;
//    private String to;
//    private String cc;
//    private String bcc;
//    @Column(length = 250)
//    private String subject;
//    @Column(length = 1000)
//    private String body;
//
//    public MessageTable(String type, String correlId, String resourceCode, String to, String cc, String bcc, String subject, String body) {
//        this.type = type;
//        this.correlId = correlId;
//        this.resourceCode = resourceCode;
//        this.to = to;
//        this.cc = cc;
//        this.bcc = bcc;
//        this.subject = subject;
//        this.body = body;
//    }
//}
