package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "ConsentSource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentSource extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ConsentSource";
    private  String classname;
    @Id
    private Long id;
    private String ip_address;
    private Map<String,String> geolocation;
    private String device_info;
    private String device_type;
    private String device_name;
    private String device_version;
    private String device_model;

    public void setClassname(String classname) {
        this.classname = "ConsentSource";
    }


}