package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

import java.util.Date;

@Data
public class ConsumerPrescriptionResponse extends ConsumerBaseResponse {
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

}
