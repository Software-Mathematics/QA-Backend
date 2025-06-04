package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DosesAggregation {
    private VisitDTO visit;
    private PatientDTO patient;
    private List<DosesDTO> doseslist;
}
