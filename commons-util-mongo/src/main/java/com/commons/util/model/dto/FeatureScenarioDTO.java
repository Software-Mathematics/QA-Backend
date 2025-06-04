package com.commons.util.model.dto;

import com.commons.data.entity.FeatureDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureScenarioDTO extends BaseDtoI {

    //    private Long id;
    private String name;
    private String description;
    private List<FeatureDataModel> data_model;
    private String code;

    private String classname;

}