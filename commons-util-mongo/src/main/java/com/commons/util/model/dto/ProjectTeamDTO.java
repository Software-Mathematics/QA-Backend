package com.commons.util.model.dto;

import com.commons.data.entity.ProjectMember;
import lombok.Data;

@Data
public class ProjectTeamDTO extends BaseDtoI {

    private String code;
    private String projectcode;
    private String projectname;
    private ProjectMember projectteams;
    private String parentcode;
    private String parentHierarchicalcode;
    private String type;
    private  String classname;
}
