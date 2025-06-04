package com.commons.util.model.dto;

import com.commons.data.entity.WareHouseMaster;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectAggregation {

    private Set<ProjectDTO> parentprojectlist;
    private Set<WareHouseMaster> parentwarehouselist;
    private Set<WareHouseMaster> childwarehouselist;
    private Set<ProjectDTO> projectlist;
    private Set<ProjectPincodeDTO> pincodelist;
    private Set<MMUCreationDTO> mmulist;
}
