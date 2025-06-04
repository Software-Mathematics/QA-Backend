package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeMasterDTO extends BaseDtoI{
    private String name;
    private String displayname;
    private String description;
    //    private String alias;
    private String uom;
    private String uomdescription;
    private String lowerlimit;
    private String upperlimit;
    private String displayflag;
    private String type;
    private String value;
    private String code;
    private String modelno;
    private String stationcode;
    private String machinecode;
    private String processid;
    private String classname;
}
