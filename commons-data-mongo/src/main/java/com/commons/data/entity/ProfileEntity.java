package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProfileEntity")
@Data
public class ProfileEntity extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ProfileEntity";
    private  String classname;
    @Id
    private Long id;
    private String resourcecode;
    private String resourcename;
    private String groupcode;
    private String rolecode;
    private String rolename;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String resetflag;
    private String token;
    private String profileid;
    private String emailid;
    private String phoneno;
    private String designationcode;
    private String designationname;
    private String departmentcode;
    private String departmentname;
    private String dateofbirth;
    private String mmucode;
    private PinCode pincode;
    private String referalcode;
    private String gender;
    private String type;

    public void setClassname(String classname) {
        this.classname = "ProfileEntity";
    }


}
