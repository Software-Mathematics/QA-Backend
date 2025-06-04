package com.commons.data.entity;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ProjectPincode")
@Data

public class ProjectPincode  extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "ProjectPincode";

    @Id
    private Long id;
    private String code;
    private String projectcode;
    private String projectname;
    private PinCode pincode;
    private String completeaddress;
    private  String classname;


    public void setClassname(String classname) {
        this.classname = "ProjectPincode";
    }

}
