package com.commons.util.model.dto;

import lombok.Data;

@Data
public class StatusMasterDTO extends BaseDtoI {
    private String name;
    private String code;
    private String description;
    private String statusof;
    private String resourcecode;
    private String rolecode;
    private String classname;
}
