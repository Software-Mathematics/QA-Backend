package com.commons.util.model.dto;

import lombok.Data;

@Data
public class DesignationDTO extends BaseDtoI{
//    private Long id;
    private String name;
    private String code;
    private String hierarchicalcode;
    private String parenthierarchicalcode;
    private String description;
    private String deptcode;
    private String reporting;
    private String type;
    private String classname;
}
