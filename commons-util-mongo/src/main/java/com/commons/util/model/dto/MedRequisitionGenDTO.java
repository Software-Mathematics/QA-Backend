package com.commons.util.model.dto;

import com.commons.data.entity.Itembatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MedRequisitionGenDTO extends BaseDtoI {
    private String code;
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
    @JsonProperty("mmuitemcode")
    private String mmuitemCode;
    private String sapitemcode;
    @JsonProperty("quantityrqst")
    private String quantityrqst;
    @JsonProperty("itemtype")
    private String itemtype;
    @JsonProperty("quantityapproved")
    private String quantityapproved;
    @JsonProperty("quantityrcvd")
    private String quantityrcvd;
    @JsonProperty("quantitypndg")
    private String quantitypndg;
    @JsonProperty("uom")
    private String uom;
    private String mmucode;
    private String mmuname;
    private String remarks;
    private String whname;
    private String vendor;
    private String ponumber;
    private String unitprice;
    private String netprice;
    private String name;
    private Itembatch itembatch;
    private String classname;
}
