package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@Data
public class Employee extends BaseEntity{ @Transient
public static final String SEQUENCE_NAME = "Employee";
    @Id
    private Long id;
    private String empid;
    private String name;
    private String role;
    private String address;

    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "Employee";
    }

}