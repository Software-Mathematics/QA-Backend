package com.commons.util.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PrescriptionDTO extends BaseDtoI {

    private String presid;
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
    private String Reasontoreferal;
    private String additionalnotes;
    private String mmucode;
    private String name;
    private String age;
    private String classname;
    private String diagnosiscategory;
    private Date nextfollowupdate;
    private List<String> diseaselist;
}
