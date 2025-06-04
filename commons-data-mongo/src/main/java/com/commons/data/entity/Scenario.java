package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Scenario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scenario extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Scenario";
    private  String classname;
    @Id
    private Long id;
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

    public void setClassname(String classname) {
        this.classname = "Scenario";
    }

}
