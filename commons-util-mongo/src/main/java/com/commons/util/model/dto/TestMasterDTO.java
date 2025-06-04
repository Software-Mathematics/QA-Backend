package com.commons.util.model.dto;

import com.commons.data.entity.Investigation;
import lombok.Data;

import java.util.List;

@Data
public class TestMasterDTO extends BaseDtoI {

    private String code;
    private String testname;
    private String name;
    private String value;
    private String uom;
    private String rangetype;
    private String range;
    private String reagent;
    private String classname;
    private List<Investigation> investigationlist;

}
