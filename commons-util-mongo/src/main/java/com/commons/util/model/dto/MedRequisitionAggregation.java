package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedRequisitionAggregation {
    private MedRequisitionGenTxnDTO medrequisitiongentxndto;
    private List<MedRequisitionGenDTO> medrequisitiongendtoList;
}
