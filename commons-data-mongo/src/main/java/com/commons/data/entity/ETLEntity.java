package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "ETLEntity")
public class ETLEntity extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ETLEntity";
    @Id
    private Long id;
    private int sequence;
    private String insertstm;
    private String selectstm;
    private String sourcetargetmap;
    private String idmap;
    private Date lastrun;
    private String insertfield;
    private String selectfield;
    private String group;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "ETLEntity";
    }
}
