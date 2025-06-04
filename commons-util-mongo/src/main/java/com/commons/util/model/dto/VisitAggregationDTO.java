package com.commons.util.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class VisitAggregationDTO {
    private PatientDTO patient;
    private VisitDTO visit;
    private List<VitalsDTO> vitallist;
    private List<PrescriptionDTO> prescriptionlist;
    private EventDTO slot;
    @JsonIgnore
    private List<BookingDTO> booking;
}
