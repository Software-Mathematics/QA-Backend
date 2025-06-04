package com.commons.util.model.dto;

import com.commons.data.entity.DataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioDTO extends BaseDtoI {

    //    private Long id;
    private String name;
    private String description;
    private String type;
    private String base_url;
    private String endpoint;
    private String mapping_type;
    private Map<String,Object> headers;
    private List<DataModel> data_model;
    private String code;
    private String transaction_id;

    private String classname;

}