package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "CommunicationRecord")
@Data
public class CommunicationRecord extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "CommunicationRecord";
    @Id
    private Long id;
    private String profileid;
    private String name;
    private String type;
    private String value;
    private int priority;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "CommunicationRecord";
    }

}
