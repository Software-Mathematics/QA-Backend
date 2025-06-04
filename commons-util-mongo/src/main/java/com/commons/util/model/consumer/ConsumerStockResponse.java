package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerStockResponse extends ConsumerBaseResponse {

    private String stockreference;
    private String mmuid;
    private String whid;
    private String blockid;
    private String mmuitemcode;
    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;
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
