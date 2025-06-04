package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "DataSet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSet extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "DataSet";
    private  String classname;
    @Id
    private Long id;
    private String baseUrl;
    private String endpoint;
    private String mappingType;
    private List<Map<String, Object>> data_model;
    private Map<String, Object> header;
    private DatasetScenario scenario;


    public void setClassname(String classname) {
        this.classname = "DataSet";
    }

}
