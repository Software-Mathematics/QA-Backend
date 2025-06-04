package com.commons.util.model.dto;

import lombok.Data;

@Data
public class InvestigationDTO {
    private String name;
    private String description;
    private String code;
    private String range;
    private String uom;
    private String value;
    private String gender;
}
