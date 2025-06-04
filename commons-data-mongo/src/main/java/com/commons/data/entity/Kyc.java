package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Kyc")
@Data
public class Kyc extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Kyc";
    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
    private String pannumber;
    private String bankaccountnumber;
    private String ifsccode;
    private String adharnumber;
    private String nomineename;
    private String relationwith;
    private  String classname ;

    public void setClassname(String classname) {
        this.classname = "Kyc";
    }



}
