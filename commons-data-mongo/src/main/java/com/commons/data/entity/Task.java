package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Document(collection = "Task")
public class Task {
    @Transient
    public static final String SEQUENCE_NAME = "Task";
    private  String classname ;
    @Id
    private Long id;
    private Integer sequence;
    private String type;
    private String name;
    private String description;
    private LocalDate startdate;
    private LocalTime starttime;
    private LocalDate enddate;
    private LocalTime endtime;
    private String duration;
    private String durationuom;
//    private List<> dependency;
//    private String dependency;
    public void setClassname(String classname) {
        this.classname = "Task";
    }
}
