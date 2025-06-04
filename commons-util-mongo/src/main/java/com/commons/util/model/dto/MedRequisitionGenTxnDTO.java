package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MedRequisitionGenTxnDTO extends BaseDtoI {
    @JsonProperty("mrnumber")
    private String mrnumber; //Autogeneration
    @JsonProperty("mmuid")
    private String mmuid;
    @JsonProperty("whid")
    private String whid;
    private String owhid;
    private String owhname;
    @JsonProperty("blockid")
    private String blockid;
    @JsonProperty("mmucode")
    private String mmucode;
    private String comment;
    private String test;
    private String mmuname;
    private String remarks;
    private String whname;
    private String vendor;
    private String ponumber;
    private String classname;

}
