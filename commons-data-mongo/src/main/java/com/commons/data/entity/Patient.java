package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patient")
@Data
public class Patient extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Patient";
    @Id
    private Long id;
    private String profileid;
//    @Indexed(unique = true)
    private String patientid;
//    @Indexed(unique = true)
    private String patienttempid;
    private String name;
    private String dob;
    private String mobileno;
    private String age;
    private String bpl;
    private String plw;
    private String disability;
    private String documenttype;
//    @Indexed(unique = true)
    private String documentno;
    private String addressid;
    private String emailid;
    private String emergencycontact;
    private String emergencyname;
    private String visitid;
    private String village;
    private String villageshortname;
    private String address;
    private String gender;
    private String emergencyaddress;
    private  String classname ;
    private String martialstatus;
    private String occupation;
    private String education;
    private String typeofrationcard;
    private String pandlwomen;
    private String relation;
    public void setClassname(String classname) {
        this.classname = "Patient";
    }
}
