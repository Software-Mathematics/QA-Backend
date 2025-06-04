package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AttributeMaster")
@Data
public class AttributeMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "AttributeMaster";
    @Id
    private Long id;
    private String name;
    private String displayname;
    private String description;
//    private String alias;
    private String uom;
    private String uomdescription;
    private String lowerlimit;
    private String upperlimit;
    private String displayflag;
    private String type;
    private String value;
    private String classname;
    private String code;
    private String modelno;
    private String stationcode;
    private String machinecode;
    private String processid;

    public void setClassname(String classname) {
        this.classname = "AttributeMaster";
    }
}
