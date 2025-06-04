package com.commons.util.model.dto;

import lombok.Data;

@Data
public class VitalsMasterDTO extends BaseDtoI {
    private String code;
    private String name;
    private String value;
    private String uom;
    private String type;
    private String priority;
    private String range;
    private String classname;

}
