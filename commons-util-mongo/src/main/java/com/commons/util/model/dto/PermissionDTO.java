package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO extends BaseDtoI{

    private String code;
    private Map<String, Object> permission;
    private String classname;

}
