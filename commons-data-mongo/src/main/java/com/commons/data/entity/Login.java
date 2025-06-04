package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Login")
@Data
public class Login extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Login";
    private String classname;
    @Id
    private Long id;
    private String code;
    private String emailid;
    private String mobileno;
    private String username;
    private String password;
    private String profileid;
    private String isloggedin;
    private String logincount;
    private String isactive;
    private String resourcecode;
    private String resourcename;
    private String name;
    private String firstname;
    private String lastname;
    private String title;
    private String rolename;
    private String relation;
    private String rolecode;
    private String age;
    private String experience;
    private String sex;
    private String designationcode;
    private String designationname;
    private String departmentcode;
    private String departmentname;
    private String description;
    private String bloodgroup;
    private String language;
    private String type;
    private String phoneno;
    private String dateofbirth;
    private String isfamilymember;
    private String gender;
    private String mmucode;
    private String firmname;
    private String pincode;
    private List<Role> roles;
    private String devicetoken;

    public void setClassname(String classname) {
        this.classname = "Login";
    }


}
