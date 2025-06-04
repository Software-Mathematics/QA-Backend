package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStatusDTO extends BaseDtoI{
    private String stationname;
    private String stationcode;
    private String name;
    private String itemcode;
    private String classname ;
    private String type;
    private String bypass;
    private String modelno;
    private String setuppart;
    private String rework;
    private String reworkcount;
    private String totalreworkcount;
    private String linecode;
    private String linename;
    private Map<String, Object> attributes;
    private String updateddate;
}
