package com.commons.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "ItemMaster")
@Data
public class ItemMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ItemMaster";
    @Id
    private Long id;
    private String name;
    private String type;
//    @JsonProperty("mmucode")
//    @Indexed(unique = true)
    private String mmucode; // Autogeneration
//    @JsonProperty("sapcode")
    private String sapcode;
    private String nextstationcode;
    private String stationcode;
    private String stationname;
    private String classname ;
    private String code;
    private String description;
    private String uom ;
    private String packaging ;
    private String taxable ;
    private String taxcode ;
    private String taxcategory ;
    private String bypass;
    private String modelno;
    private String setuppart;
    private String rework;
    private String reworkcount;
    private String totalreworkcount;
    private Map<String, Object> attributes;
    private String pricename;
    private String linecode;
    private String linename;
    private String updateddate;


    public void setClassname(String classname) {
        this.classname = "ItemMaster";
    }
}


