package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO extends BaseDtoI{
//    private Long id;
    private String profileid;
    private String resourcecode;
    private Date dateofappointment;
    private String empid;
    private String emptype;
    private String shift;
    private String grade;
    private String probation;
    private Date dateofconfirmation;
    private String department;
    private String section;
    private String designation;
    private String costcentre;
    private String previousorgname;
    private Date dateofjoining;
    private Date dateofleaving;
    private String reasonofleaving;
    private String experienceinmonth;
    private String experienceinyear;
    private String classname;
}
