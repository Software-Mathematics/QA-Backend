package com.commons.util.model.dto;

import com.commons.data.entity.Address;
import lombok.Data;

@Data
public class VisitDTO extends BaseDtoI {

    private String visitid;
//    private String latLong;
    private String patientvisitid;
    private String patientid;
    private String patienttempid;
    private String firstscreening;
    private String doctorid;
    private String mmucode;
    private String visittype;
    private String classname;
    private String visitcategory;
    private String followuptovisit;
    private String presid;
    private String paymenttype;
    private String address;
    private String doctorname;
    private String parentvisitid;
    private String childvisitid;
    private String profileid;
    private String category;
    private String reasonofvisit;
    private String patientservice;
    private String ordernumber;

}
