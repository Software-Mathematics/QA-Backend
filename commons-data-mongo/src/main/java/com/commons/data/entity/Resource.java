package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Resource")
public class Resource extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Resource";
    private  String classname ;
    @Id
    private Long id;
    private String resourcecode;
    private String hierarchicalcode;
    private String name;
    private String description;
    private String parentcode;
    private String type;
    private String subtype;

//    private String status;

    public void setClassName(String className) {
        this.classname = "Resource";
    }

}
