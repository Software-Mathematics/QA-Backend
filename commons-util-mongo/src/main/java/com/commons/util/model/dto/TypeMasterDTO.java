package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TypeMasterDTO extends BaseDtoI {
    private String name;
    private String code;
    private String description;
    private String category;
    private String resourcecode;
    private String rolecode;
    private String classname;
    private String categorytype;
    private List<String> nameattributes;
    private List<String> uniqueattributes;
    private List<String> attributes;
    private String displaytext;
    private String basename;
    private String conversionfactor;
}
