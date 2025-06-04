package com.commons.util.model.dto;

import com.commons.data.entity.Usercategory;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionAggregationDTO  {
    private PrescriptionDTO prescription;
    private PatientDTO patient;
    private VisitDTO visit;
    private List<DosesDTO> doseslist;
    private List<VitalsDTO> vitalslist;
    private List<LabTestsDTO> labtestslist;
}
