package com.commons.data.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProjectMMU")
@Data
public class ProjectMMU extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ProjectMMU";
    private String code;
    private String projectcode;
    private String projectname;
    private MMU mmu;
    private String parentcode;
    private String parentHierarchicalcode;
    private  String classname;
    private String type;
    @Id
    private Long id;
    public void setClassname(String classname) {
        this.classname = "ProjectMMU";
    }
}
