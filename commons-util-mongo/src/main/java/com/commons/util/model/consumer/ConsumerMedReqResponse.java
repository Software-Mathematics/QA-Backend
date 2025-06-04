package com.commons.util.model.consumer;

import com.commons.data.entity.Itembatch;
import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerMedReqResponse extends ConsumerBaseResponse {
    private String code;
    private String mrnumber;
    private String mmuid;
    private String whid;
    private String owhid;
    private String owhname;
    private String blockid;
    private String mmuitemCode;
    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;
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
