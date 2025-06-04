package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bankaccounts1")
@Data
public class Bankaccounts1 extends BaseEntity  {

    @Transient
    public static final String SEQUENCE_NAME = "Bankaccounts1";
    @Id
    private Long id;
    private String bankname;
    private String ifsc;
    private String accountno;
    private String holdername;
    private String profileid;
    private  String classname;
    public void setClassname(String classname) {
        this.classname = "Bankaccounts1";
    }
}

