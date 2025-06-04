package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Statutory_Records")
@Data
public class StatutoryRecords extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "StatutoryRecords";
    private  String classname ;
    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
//    private String documentofproof;
    private String uannumber;
    private String pf;
    private String epsmember;
    private String pfnominee;
    private String esicnumber;
    private String esicnominee;
    private String lwfnumber;
    private String lefnominee;

    public void setClassname(String classname) {
        this.classname = "StatutoryRecords";
    }


}
