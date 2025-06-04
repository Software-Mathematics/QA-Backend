package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "FeatureScenario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureScenario extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "FeatureScenario";
    private  String classname;
    @Id
    private Long id;
    private String name;
    private String description;
    private List<FeatureDataModel> data_model;
    private String code;

    public void setClassname(String classname) {
        this.classname = "FeatureScenario";
    }

}
