package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DosesTxnDTO extends BaseDtoI{
    private String classname;
    @JsonProperty("pdnumber")
    private String pdnumber; //autogenerator
    private String presid;
    private String mmucode;
    private String visitid;
}
