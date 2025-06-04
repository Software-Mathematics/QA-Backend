package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;




@Data

public class OtpsDTO extends BaseDtoI {


    private String userid;
    private String otp;
    private String otptype;
    private String typevalue;
    private String resourcecode;
    private String resourcename;
    private LocalDateTime createdat;
    private LocalDateTime expiresat;
    private LocalDateTime confirmedat;
    private Map<String, Object> extra_fields;
    private String classname;
}
