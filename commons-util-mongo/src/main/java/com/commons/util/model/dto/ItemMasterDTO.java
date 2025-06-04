package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ItemMasterDTO extends BaseDtoI {
    private String name;
    private String type;
    @JsonProperty("mmucode")
    private String mmucode; // Autogeneration
    @JsonProperty("sapcode")
    private String sapcode;
    private String stationcode;
    private String nextstationcode;
    private String stationname;
    private String classname;
    private String code;
    private String description;
    @JsonProperty("uom")
    private  String uom;
    private String packaging;
    private String taxable ;
    private String taxcode ;
    private String taxcategory ;
    private String bypass;
    private String setuppart;
    private String rework;
    private String modelno;
    private String reworkcount;
    private String totalreworkcount;
    private Map<String, Object> attributes;
    private String pricename;
    private String linecode;
    private String linename;
    private String updateddate;
    
}
