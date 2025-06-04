package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineDTO extends BaseDtoI{
    private String code;
    private String name;
    private String description;
    private String document;
    private String type;
    private String hierarchicalcode;
    private String parentcode;
    private String parenthierarchicalcode;
}
