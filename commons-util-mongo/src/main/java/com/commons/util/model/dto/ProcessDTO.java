package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDTO extends BaseDtoI{
    private String stationsequence;
    private String code;
    private String name;
    private String bottleneckcycletime;
    private String operatorefficiencyfactor;
}
