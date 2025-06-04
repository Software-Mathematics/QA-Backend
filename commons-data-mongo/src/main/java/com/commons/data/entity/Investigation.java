package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investigation {
    private String name;
    private String description;
    private String code;
    private String range;
    private String uom;
    private String value;
    private String gender;
    private String remarks;
}
