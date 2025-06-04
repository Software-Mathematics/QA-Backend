package com.commons.util.model.dto;

import lombok.Data;

@Data
public class MappingDTO extends BaseDtoI {
    private String type;
    private String sub_type;
    private String name;
    private String description;
    private String mapping_id;
    private String value;
    private String classname;
}
