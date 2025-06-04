package com.commons.util.model.dto;

import com.commons.data.entity.PartStatusMaster;
import lombok.Data;

import java.util.List;

@Data
public class StationPartStatusMasterAggregation {
    private String name;
    private String code;
    private String description;
    private String bypass;
    private String setuppart;
    private String rework;
    private String totalreworkcount;
    //    private List<String> tags;
    private String hierarchicalcode;
    private String parentcode;
    private String parenthierarchicalcode;
    private List<PartStatusMaster> partStatusMasters;
}
