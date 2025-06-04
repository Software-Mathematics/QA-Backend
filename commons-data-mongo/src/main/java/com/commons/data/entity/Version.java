package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Version")
@Data
public class Version extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Version";
    private  String classname;
    @Id
    private Long id;
    private String code;
    private String versionno;
    private String name;
    private String desc;
    private String os;
    private List  devices;
    private String priority;
    private String major;
    private String minor;
    private String build;
    private String ischange;
    private String update;
    private String url;

    public void setClassname(String classname) {
        this.classname = "Version";
    }

}
