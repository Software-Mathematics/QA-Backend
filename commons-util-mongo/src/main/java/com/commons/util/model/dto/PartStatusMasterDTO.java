package com.commons.util.model.dto;


import lombok.Data;

@Data
public class PartStatusMasterDTO extends BaseDtoI {
    private String stationname;
    private String stationcode;
    private String nextstationcode;
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
    private String updateddate;
    public void setClassname(String classname) {
        this.classname = "PartStatusMaster";
    }
}
