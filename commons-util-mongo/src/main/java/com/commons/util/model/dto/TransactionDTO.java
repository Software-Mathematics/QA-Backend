package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDTO extends BaseDtoI {

    private String mrnumber;
    private String mmuid;
    private String whid;
    private String blockid;
    private String reqLineitemid;
    private String flowtype;
    private String referencenumber;//Autogeneration
    private String quantity;
    private String uom;
    private String sourcereference;
    private String targetreference;
    private String itemname;
    private String itemtype;
    private String mmucode;
    private String ponumber;

    private String classname;


}
