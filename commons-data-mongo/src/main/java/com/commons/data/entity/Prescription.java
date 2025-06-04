package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "Prescription")
@Data
public class Prescription extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Prescription";


    private  String classname;
    @Id
    private Long id;
    @Indexed(unique = true)
    private String presid;
//    @Indexed(unique = true)
    private String prestempid;
    private String patientid;
    private String parentpresid;
    private String doctor;
    private String visitid;
    private String chiefcomplaint;
    private String history;
    private String diagnosistext;
    private String diagnosisfile;
    private String prestype;
    private String refereddoctor;
    private String reasontoreferal;
    private String additionalnotes;
    private String mmucode;
    private String name;
    private String age;
    private String diagnosiscategory;
    private Date nextfollowupdate;
    private List<String> diseaselist;


    public void setClassname(String classname) {
        this.classname = "Prescription";
    }
}
