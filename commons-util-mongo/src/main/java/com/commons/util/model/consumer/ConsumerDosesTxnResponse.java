package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerDosesTxnResponse extends ConsumerBaseResponse {
    private String classname;
    private String pdnumber;
    private String presid;
    private String mmucode;
    private String visitid;

}
