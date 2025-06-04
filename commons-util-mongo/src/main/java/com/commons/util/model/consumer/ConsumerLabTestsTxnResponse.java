package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerLabTestsTxnResponse extends ConsumerBaseResponse {
    private String ptnumber;
    private String presid;
    private String mmucode;
    private String classname;
    private String visitid;

}
