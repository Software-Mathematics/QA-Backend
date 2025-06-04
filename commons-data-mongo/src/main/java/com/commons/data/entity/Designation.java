package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Designation")
@Data
public class Designation extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "Designation";
    @Id
    private Long id;
    private String name;
    private String code;
    private String hierarchicalcode;
    private String parenthierarchicalcode;
    private String description;
    private String deptcode;
    private String reporting;
    private String type;
    private  String classname;
    public void setClassname(String classname) {
        this.classname = "Designation";
    }
}
