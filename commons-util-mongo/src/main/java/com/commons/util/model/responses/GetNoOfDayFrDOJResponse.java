package com.commons.util.model.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class GetNoOfDayFrDOJResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int years;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int months;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int days;
}
