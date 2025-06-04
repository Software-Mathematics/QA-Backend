package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Taskk")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taskk extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Taskk";
    private  String classname;
    @Id
    private Long id;
    private String name;
    private String description;
    private String type;
    private String code;
    private Object additional_fields;
    private String actual_duration;
    private String actual_startdate;
    private String actual_enddate;
    private String estimated_duration;
    private String estimated_startdate;
    private String estimated_enddate;


    public void setClassname(String classname) {
        this.classname = "Taskk";
    }

}