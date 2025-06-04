package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LabTestsTxnDTO extends BaseDtoI{
    @JsonProperty("ptnumber")
    private String ptnumber;
    private String presid;
    private String mmucode;
    private String classname;
    private String visitid;
}
