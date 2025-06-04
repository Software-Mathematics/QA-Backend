package com.commons.data.entity;

import lombok.Data;

import java.util.Map;

@Data
public class TypeObject {
    private Map<String, String> value;
    private String valuetype;
}
