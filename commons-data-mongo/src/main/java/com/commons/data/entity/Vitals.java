package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vitals")
@Data
public class Vitals extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Vitals";
    private  String classname ;
    @Id
    private Long id;
    private String code;
    private String name;
    private String value;
    private String uom;
    private String presid;
    private String visitid;
    private String mmucode;
    private String type;
    private String priority;
    private String range;
    public void setClassname(String classname) {
        this.classname = "Vitals";
    }
}
