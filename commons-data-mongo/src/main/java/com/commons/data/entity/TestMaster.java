package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TestMaster")
@Data
public class TestMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "TestMaster";
    private String classname ;
    @Id
    private Long id;
    private String code;
    private String testname;
    private String name;
    private String value;
    private String uom;
    private String rangetype;
    private String range;
    private String reagent;
    private List<Investigation> investigationlist;

    public void setClassname(String classname) {
        this.classname = "TestMaster";
    }

}
