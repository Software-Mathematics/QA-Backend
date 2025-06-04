package com.commons.util.model.dto;

import lombok.Data;

@Data
public class QualificationDTO extends BaseDtoI{
	
//    private Long id;
    private String profileid;
    private String qualificationname;
    private String qualificationtype;
    private String qualificationmode;
    private String qualificationboard;
    private String qualificationgradetype;
    private String qualificationgrade;
    private String startdate;
    private String enddate;
    private String classname;
    
}
