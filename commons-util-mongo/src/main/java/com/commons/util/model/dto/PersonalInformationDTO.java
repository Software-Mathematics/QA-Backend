package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonalInformationDTO extends BaseDtoI {

//    private Long id;
    private String profileid;
    private String firstname;
    private String lastname;
    private String nickname;
    private String fathername;
    private String mothername;
    private String dateofbirth;
    private String spousename;
    private String maritalstatus;
    private String qualification;
    private String sex;
    private String bloodgroup;
    private String emailid;
    private String phoneno;
    private String description;
    private String classname;
    private List<String> languages;
    private String rolecode;
    private String rolename;
    private int age;
    private String name;


}
