package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDTO extends BaseDtoI{
    private Long id;
    private String name;
    private String type;
    private String category;
    private String code;
    private String description;
    private String resourcecode;

}
