package com.commons.util.model.dto;

import lombok.Data;

import java.util.Map;


@Data
public class UpdateDTO {
    private Map<String, Object> criteria;
    private Map<String, Object> dto;
}
