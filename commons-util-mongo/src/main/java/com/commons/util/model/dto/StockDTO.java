package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockDTO extends BaseDtoI {
    private String stockreference; //Autogeneration
    @JsonProperty("mmuid")
    private String mmuid;
    @JsonProperty("whid")
    private String whid;
    @JsonProperty("blockid")
    private String blockid;
    @JsonProperty("mmuitemcode")
    private String mmuitemcode;
    @JsonProperty("sapitemcode")
    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;
    @JsonProperty("uom")
    private String uom;
    private String name;
    private String description;
    private String openingstock;
    private String closingstock;
    private String currentstock;
    private String classname;
    private String mmucode;
    private String blockname;
    private String whname;
    private String mmuname;
    private String itemcode;
    private String quantityindemand;
    private String consumption;
}
