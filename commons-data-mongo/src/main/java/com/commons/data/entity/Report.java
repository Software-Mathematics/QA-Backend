package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "Report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Report";
    private  String classname;
    @Id
    private Long id;
    private String code;
    private String linecode;
    private String linename;
    private String modelno;
    private Date date;
    private String type;
    private Event shift;
    private String laststation;
    private String bottleneckcycletime;
    private String operatorefficiencyfactor;
    private String targetno;
    private String actualngpart;
    private String actualokpart;
    private String loss;
    private String actuallineefficiency;
    private String lineefficiencyloss;
    private String availabilityrate;
    private String performancerate;
    private String qualityrate;
    private String oee;
    private String oeeloss;



    public void setClassname(String classname) {
        this.classname = "Report";
    }


}
