package com.commons.util.model.dto;

import com.commons.data.entity.PinCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WareHouseMasterDTO extends  BaseDtoI {
    private String name;
    private String type;
    private String pincode;
    private String address;
    @JsonProperty("whsapcode")
    private String whsapcode;
    @JsonProperty("whmmucode")
    private String whmmucode; //Autogeneration
    private PinCode pincodeaddress;
    private String classname;
}
