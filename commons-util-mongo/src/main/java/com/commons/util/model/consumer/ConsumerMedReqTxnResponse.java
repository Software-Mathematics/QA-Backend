package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerMedReqTxnResponse extends ConsumerBaseResponse {
    private String mrnumber;
    private String mmuid;
    private String whid;
    private String owhid;
    private String owhname;
    private String blockid;
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
