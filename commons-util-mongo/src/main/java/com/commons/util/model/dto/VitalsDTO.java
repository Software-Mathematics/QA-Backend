package com.commons.util.model.dto;

import lombok.Data;

@Data
public class VitalsDTO extends BaseDtoI {

    private String code;
    private String name;
    private String value;
    private String uom;
    private String presid;
    private String visitid;
    private String type;
    private String mmucode;
    private String classname;
    private String priority;
    private String range;
}