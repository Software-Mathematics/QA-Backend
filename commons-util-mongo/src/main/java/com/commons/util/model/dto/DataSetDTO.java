package com.commons.util.model.dto;

import com.commons.data.entity.DatasetScenario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSetDTO extends BaseDtoI {

    //    private Long id;
    private String baseUrl;
    private String endpoint;
    private String mappingType;
    private List<Map<String, Object>> data_model;
    private Map<String, Object> header;
    private DatasetScenario scenario;

    private String classname;

}