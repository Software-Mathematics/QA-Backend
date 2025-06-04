package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentDTO extends BaseDtoI{
//    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("hierarchicalcode")
    private String hierarchicalcode;
    @JsonProperty("description")
    private String description;
    @JsonProperty("parentdepcode")
    private String parentdepcode;
    @JsonProperty("parenthierarchicalcode")
    private String parenthierarchicalcode;
    private String type;
    private String classname;
}
