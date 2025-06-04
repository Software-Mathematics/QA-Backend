package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO extends BaseDtoI {
    private String state;
    private String name;
    private String type;
    private String icon;
    private String role;
    private String landingurl;
    private String resourcecode;
    private Integer level;
    private String code;
    private String hierarchycode;
    private String parentcode;
    private List<Object> routes;
    private String classname;
}
