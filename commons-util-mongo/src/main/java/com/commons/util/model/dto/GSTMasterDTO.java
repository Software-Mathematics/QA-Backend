package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSTMasterDTO extends BaseDtoI {

    private String sno;
    private String heading;
    private String type;
    private String description;
    private String schedules;
    private String condition;
    private String compensation;
    private String cgst;
    private String sgst;
    private String igst;
    private String code;

}