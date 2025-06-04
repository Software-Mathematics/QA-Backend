package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Scheme")
@Data
public class Scheme extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Scheme";
    private  String classname ;
    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
    private String name;
    private String type;
//    @Column(length = 1000)
    private String description;

    public void setClassName(String className) {
        this.classname = "Scheme";
    }
}
