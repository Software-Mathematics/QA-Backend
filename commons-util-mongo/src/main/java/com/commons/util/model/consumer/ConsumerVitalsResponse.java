package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerVitalsResponse extends ConsumerBaseResponse {
    private String code;
    private String name;
    private String value;
    private String uom;
    private String presid;
    private String visitid;
    private String type;
    private String mmucode;
    private String classname;
    private String priority;
    private String range;
}
