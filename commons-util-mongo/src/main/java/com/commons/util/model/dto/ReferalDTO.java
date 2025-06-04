package com.commons.util.model.dto;

import lombok.Data;

@Data
public class ReferalDTO extends BaseDtoI {

    private String referalid; //autogeneration
    private String patientid;
    private String mmucode;
    private String doctor;
    private String priortreatment;
    private String reasonforreferal;
    private String presentssymptoms;
    private String additionalnotes;
    private  String classname;
    private String visitid;
    private String presid;

}
