package com.commons.util.model.dto;

import lombok.Data;
@Data
public class FrequencyMasterDTO  extends BaseDtoI {

    private String code;
    private String description;
    private String quantity;
    private String classname;
    private String countperday;
}
