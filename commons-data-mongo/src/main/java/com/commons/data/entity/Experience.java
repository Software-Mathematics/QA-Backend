package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Experience")
@Data
public class Experience extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Experience";
    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
    private Date dateofappointment;
    private String empid;
    private String emptype;
    private String shift;
    private String grade;
    private String probation;
    private Date dateofconfirmation;
    private String department;
    private String section;
    private String designation;
    private String costCentre;
    private String previousorgname;
    private Date dateofjoining;
    private Date dateofleaving;
    private String reasonofleaving;
    private String experienceinmonth;
    private String experienceinyear;
    private  String classname;
    public void setClassname(String classname) {
        this.classname = "Experience";
    }
}
