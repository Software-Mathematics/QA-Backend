package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "StationMaster")
@Data
public class StationMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "StationMaster";
    @Id
    private Long id;
    private String name;
    private String code;
    private String description;
    private String bypass;
    private String setuppart;
    private String rework;
    private String totalreworkcount;
//    private List<String> tags;
    private String hierarchicalcode;
    private String parentcode;
    private String parenthierarchicalcode;


}
