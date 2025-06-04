package com.commons.util.model.dto;

import com.commons.data.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class LoginDTO extends BaseDtoI
{

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
    private String classname;
    private String bloodgroup;
    private String language;
    private String isfamilymember;
    private String type;
    private String mmucode;
    private String firmname;
    private String pincode;
    private List<Role> roles;
    private String devicetoken;


}
