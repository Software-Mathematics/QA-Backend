package com.commons.util.model.dto;

import lombok.Data;


@Data
public class ResourceDTO extends BaseDtoI{
//    private Long id;
    private String resourcecode;
    private String hierarchicalcode;
    private String name;
    private String description;
    private String parentcode;

    private String type;
    private String subtype;
    private String classname;
}
