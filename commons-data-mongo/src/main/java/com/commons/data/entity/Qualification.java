package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Qualification")
@Data
public class Qualification extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Qualification";
    private  String classname ;
	@Id
    private Long id;
    private String profileid;
    private String qualificationname;
    private String qualificationtype;
    private String qualificationmode;
    private String qualificationboard;
    private String qualificationgradetype;
    private String qualificationgrade;
    private String startdate;
    private String enddate;

    public void setClassname(String classname) {
        this.classname = "Qualification";
    }

}
