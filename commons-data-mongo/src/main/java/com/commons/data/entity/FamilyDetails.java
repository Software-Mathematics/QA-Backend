package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "Family_Details")
@Data
public class FamilyDetails extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "FamilyDetails";
    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
    private String dependentname;
    private String relation;
    private Login login;
    private Date birthdate;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "FamilyDetails";
    }
}

