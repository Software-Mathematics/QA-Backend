package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StatusMaster")
@Data
public class StatusMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "StatusMaster";
    private  String classname ;
    @Id
    private Long id;
    private String name;
    private String code;
    private String description;
    private String statusof;
    private String resourcecode;
    private String rolecode;

    public void setClassname(String classname) {
        this.classname = "StatusMaster";
    }
}
