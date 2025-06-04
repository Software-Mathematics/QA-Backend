package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Visit")
@Data
public class Visit extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Visit";
    private  String classname;
    @Id
    private Long id;
    @Indexed(unique = true)
    private String visitid;
//    private String latLong;
    private String patientvisitid;
    private String patientid;
    private String patienttempid;
    private String firstscreening;
    private String doctorid;
    private String mmucode;
    private String visittype;
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





    public void setClassname(String classname) {
        this.classname = "Visit";
    }
}
