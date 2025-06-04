package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemeDTO extends BaseDtoI{
    private String profileid;
    private String resourcecode;
    private String name;
    private String type;
    private String description;
    private String classname;
}
