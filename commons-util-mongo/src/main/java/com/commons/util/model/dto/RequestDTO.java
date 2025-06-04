package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO extends BaseDtoI{
    private String profileid;
    private String type;
    private Map<String,Object> object;
}
