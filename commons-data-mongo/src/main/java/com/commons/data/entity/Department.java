package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Department")
@Data
public class Department extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Department";
    @Id
    private Long id;
    private String name;
    private String code;
    private String hierarchicalcode;
    private String description;
    private String parentdepcode;
    private String parenthierarchicalcode;
    private String type;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "Department";
    }
}
