package com.commons.data.entity;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProjectTeam")
@Data

public class ProjectTeam extends BaseEntity  {

    @Transient
    public static final String SEQUENCE_NAME = "ProjectTeam";


    @Id
    private Long id;
    private String code;
    private String projectcode;
    private String projectname;
    private ProjectMember projectteams;
    private String parentcode;
    private String parentHierarchicalcode;
    private String type;
    private  String classname;

    public void setClassname(String classname) {
        this.classname = "ProjectTeam";
    }


}
