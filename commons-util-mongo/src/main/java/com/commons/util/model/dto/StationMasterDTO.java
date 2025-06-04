package com.commons.util.model.dto;

import com.commons.data.entity.Category;
import com.commons.data.entity.CheckListMaster;
import com.commons.data.entity.Machine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationMasterDTO extends BaseDtoI{
    private String name;
    private String code;
    private String description;
//    private List<String> tags;
    private String hierarchicalcode;
    private String parentcode;
    private String parenthierarchicalcode;
    private String bypass;
    private String setuppart;
    private String rework;
    private String totalreworkcount;
    private List<Category> category;
    private List<Category> pokayoke;
    private List<Machine> machine;
}
