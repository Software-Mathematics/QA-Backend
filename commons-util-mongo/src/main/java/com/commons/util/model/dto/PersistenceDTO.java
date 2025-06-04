package com.commons.util.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PersistenceDTO extends BaseDtoI{
    private Map<String, Object> object;
}
