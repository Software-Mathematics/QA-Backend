package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "FeatureDataset")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDataset extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "FeatureDataset";
    private  String classname;
    @Id
    private Long id;
    private String feature_name;
    private String browser;
    private Map<String, List<FeatureDataModel>> datasets;
    private String feature_code;
    private String email;
    private String password;
    private String subject;
    private String type;
    private String html;

    public void setClassname(String classname) {
        this.classname = "FeatureDataset";
    }

}
