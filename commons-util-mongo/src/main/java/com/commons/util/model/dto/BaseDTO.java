package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO implements Serializable {

    private String audit_id;
    private String acter;
    private String name;
    private String value;


}


