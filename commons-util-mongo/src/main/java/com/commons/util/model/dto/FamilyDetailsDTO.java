package com.commons.util.model.dto;

import com.commons.data.entity.Login;
import lombok.Data;

import java.util.Date;

@Data
public class FamilyDetailsDTO extends BaseDtoI{
    private String profileid;
    private String resourcecode;
    private String dependentname;
    private String relation;
    private Login login;
    private Date birthdate;
    private String classname;
}
