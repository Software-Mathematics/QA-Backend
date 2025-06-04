package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DosesTxnAggregation {
    private DosesTxnDTO dosestxn;
    private List<DosesDTO> doseslist;
    private PatientDTO patient;
    private VisitDTO visit;
}
