package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetalMasterDTO extends BaseDtoI {

    //    private Long id;
    private String station_code;
    private String station_name;
    private String name;
    private String description;

    private  String classname;

}