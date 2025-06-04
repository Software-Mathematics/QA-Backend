package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KycDTO extends BaseDtoI{
    private String profileid;
    private String resourcecode;
    private String documentofproof;
    private String pannumber;
    private String bankaccountnumber;
    private String ifsccode;
    private String adharnumber;
    private String nomineename;
    private String relationwith;
    private String classname;


}
