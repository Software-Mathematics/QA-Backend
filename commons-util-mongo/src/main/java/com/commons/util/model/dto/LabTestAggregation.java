package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class LabTestAggregation {
    private VisitDTO visit;
    private PatientDTO patient;
    private List<LabTestsDTO> labtestslist;
}
