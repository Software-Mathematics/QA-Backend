package com.commons.util.model.dto;

import com.commons.data.entity.PinCode;
import lombok.Data;

@Data
public class ProjectPincodeDTO extends BaseDtoI {

    private String code;
    private String projectcode;
    private String projectname;
    private PinCode pincode;
    private String completeaddress;
    private  String classname;
}
