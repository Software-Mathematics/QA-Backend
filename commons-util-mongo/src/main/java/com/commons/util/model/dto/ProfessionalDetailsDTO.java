package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProfessionalDetailsDTO extends BaseDtoI{
//    private Long id;

    private String jobtitle;
    private String orgname;
    private String type;
    private String deptcode;
    private String profileid;
    private String designationid;
    private String reporting;
    private String empid;
    private String dateofjoin;
    private String dateofleave;
    private String reasonofleaving;
    private String firstname;
    private String lastname;
    private String emailid;
    private String phoneno;
    private String classname;
}
