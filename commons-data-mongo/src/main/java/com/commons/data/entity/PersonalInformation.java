package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "PersonalInformation")
@Data
public class PersonalInformation extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "PersonalInformation";
    private  String classname ;
	@Id
    private Long id;

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
    private List<String> languages;
    private String rolecode;
    private String rolename;
    private String resourcecode;
    private int age;
    private String name;

    public void setClassname(String classname) {
        this.classname = "PersonalInformation";
    }


}
