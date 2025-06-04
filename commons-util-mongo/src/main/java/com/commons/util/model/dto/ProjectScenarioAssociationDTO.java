package com.commons.util.model.dto;

import com.commons.data.entity.FeatureScenario;
import com.commons.data.entity.Project;
import com.commons.data.entity.Scenario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectScenarioAssociationDTO extends BaseDtoI {

    //    private Long id;
    private Project project;
    private List<Scenario> scenario;
    private List<FeatureScenario> features;
    private String type;

    private String classname;

}