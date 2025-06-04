package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends BaseDtoI{
    private Long roleid;
    private String name;
    private String rolecode;
    private String description;
    private String resourcecode;
    private String type;
    private Map<String, Object> permission;
    private String classname;

}
