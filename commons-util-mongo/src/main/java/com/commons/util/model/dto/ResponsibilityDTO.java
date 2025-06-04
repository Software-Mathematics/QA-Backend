package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponsibilityDTO {
    private String department;
    private List<String> rolelist;
}
