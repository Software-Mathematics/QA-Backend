package com.commons.util.model.dto;

import lombok.Data;

@Data
public class GroupDTO extends BaseDtoI{
    private String name;
    private String description;
    private String roles;
    private String resourcecode;
    private String classname;

}
