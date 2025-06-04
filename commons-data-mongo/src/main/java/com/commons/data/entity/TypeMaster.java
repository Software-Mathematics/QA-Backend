package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TypeMaster")
@Data
public class TypeMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "TypeMaster";
    private  String classname;
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String code;
    private String description;
    private String category;
    private String resourcecode;
    private String rolecode;
    private String categorytype;
    private List<String> nameattributes;
    private List<String> uniqueattributes;
    private List<String> attributes;
    private String displaytext;
    private String basename;
    private String conversionfactor;

    public void setClassname(String classname){
        this.classname = "TypeMaster";
    }
}
