package com.commons.util.model.dto;


import com.commons.data.entity.LocAttribute;

import lombok.Data;

@Data
public class PinCodeDTO extends BaseDtoI {
    private String classname;
    private LocAttribute state;
    private LocAttribute district;
    private LocAttribute village;
    private LocAttribute subdistrict;
    private String pincode;
    private LocAttribute city;
    private String resourcecode;
    private String type;
}
