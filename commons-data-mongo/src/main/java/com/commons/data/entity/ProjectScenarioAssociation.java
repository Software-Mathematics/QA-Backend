package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ProjectScenarioAssociation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectScenarioAssociation extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ProjectScenarioAssociation";
    private  String classname;
    @Id
    private Long id;
    private Project project;
    private List<Scenario> scenario;
    private List<FeatureScenario> features;
    private String type;

    public void setClassname(String classname) {
        this.classname = "ProjectScenarioAssociation";
    }

}
