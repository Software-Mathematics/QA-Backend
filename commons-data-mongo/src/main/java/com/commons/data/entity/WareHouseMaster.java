package com.commons.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "WareHouseMaster")
@Data
public class WareHouseMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "WareHouseMaster";
    private  String classname ;
    @Id
    private Long id;
    private String name;
    private String type;
    private String pincode;
    private String address;
    @JsonProperty("whsapcode")
    private String whsapcode;
    @JsonProperty("whmmucode")
    @Indexed(unique = true)
    private String whmmucode; //Autogeneration
    private PinCode pincodeaddress;


    public void setClassname(String classname) {
        this.classname = "WareHouseMaster";
    }

}
