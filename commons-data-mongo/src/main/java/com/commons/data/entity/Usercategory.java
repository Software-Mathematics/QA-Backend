package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usercategory")
@Data
public class Usercategory extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Usercategory";
    @Id
    private Long id;
    private String userid;
    private String profileid;
    private String resourcecode;
    private Category category;
    private  String classname;
    private String mmucode;
    public void setClassname(String classname) {
        this.classname = "Usercategory";
    }
}

