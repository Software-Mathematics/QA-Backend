package com.commons.util.model.dto;

import com.commons.data.entity.MMU;
import lombok.Data;

@Data
public class ProjectMmuDTO extends BaseDtoI{

    private String code;
    private String projectcode;
    private String projectname;
    private MMU mmu;
    private String parentcode;
    private String type;
    private String parentHierarchicalcode;
    private  String classname;

}
