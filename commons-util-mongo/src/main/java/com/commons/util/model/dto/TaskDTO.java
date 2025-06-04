package com.commons.util.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends BaseDtoI {

    //    private Long id;
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
    private  String classname;

}