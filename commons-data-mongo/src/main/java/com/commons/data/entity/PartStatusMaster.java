package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "PartStatusMaster")
@Data
public class PartStatusMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "PartStatusMaster";
    @Id
    private Long id;
    private String stationname;
    private String stationcode;
    private String nextstationcode;
    private String name;
    private String itemcode;
    private String classname ;
    private String type;
    private String bypass;
    private String modelno;
    private String setuppart;
    private String rework;
    private String reworkcount;
    private String totalreworkcount;
    private String linecode;
    private String linename;
    private String updateddate;

    public void setClassname(String classname) {
        this.classname = "PartStatusMaster";
    }
}
