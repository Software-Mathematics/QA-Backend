package com.commons.util.model.dto;

import com.commons.data.entity.FeatureDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDatasetDTO extends BaseDtoI {

    //    private Long id;
    private String feature_name;
    private String browser;
    private Map<String, List<FeatureDataModel>> datasets;
    private String feature_code;
    private String email;
    private String password;
    private String subject;
    private String type;
    private String html;

    private String classname;

}