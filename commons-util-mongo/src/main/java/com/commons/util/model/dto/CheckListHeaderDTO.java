package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListHeaderDTO extends BaseDtoI{
    private String operatorname;
    private String operatorid;
    private String code;
    private String stationname;
    private String stationcode;
    private String type;
}
