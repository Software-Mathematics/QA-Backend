package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Menu")
@Data
public class Menu extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Menu";
    @Id
    private Long id;
    private String state;
    private String name;
    private String type;
    private String icon;
    private String role;
    private String landingurl;
    private String resourcecode;
    private Integer level;
    private String code;
    private String hierarchycode;
    private String parentcode;
    private List<Object> routes;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "Menu";
    }
}
