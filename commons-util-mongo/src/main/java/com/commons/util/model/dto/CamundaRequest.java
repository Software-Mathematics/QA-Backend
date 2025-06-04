package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamundaRequest {
    private String messageName;
    private String businessKey;
    private CorrelationKey correlationKeys;
    private ProcessVariable processVariables;




}

