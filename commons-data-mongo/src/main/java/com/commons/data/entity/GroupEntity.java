package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "GroupEntity")
@Data
public class GroupEntity extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "GroupEntity";
    @Id
    private Long id;
    private String name;
    private String description;
    private String roles;
    private String resourcecode;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "GroupEntity";
    }
}
