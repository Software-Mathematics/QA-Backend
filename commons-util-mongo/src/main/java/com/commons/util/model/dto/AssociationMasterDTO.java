package com.commons.util.model.dto;

import com.commons.data.entity.Category;
import com.commons.data.entity.Machine;
import com.commons.data.entity.StationMaster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationMasterDTO extends BaseDtoI {

    //    private Long id;
    private StationMaster station;
    private List<Category> checklist;
    private List<Category> pokayoke;
    private List<Machine> machine;
    private  String classname;

}