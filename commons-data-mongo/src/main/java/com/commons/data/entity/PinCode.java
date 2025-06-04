package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PinCode")
@Data
public class PinCode  extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "PinCode";

    private  String classname;
    @Id
    private Long id;
    private LocAttribute state;
    private LocAttribute district;
    private LocAttribute village;
    private LocAttribute subdistrict;
    private String pincode;
    private LocAttribute city;
    private String resourcecode;
    private String type;

    public void setClassname(String classname) {
        this.classname = "PinCode";
    }
}

