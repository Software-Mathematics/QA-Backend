package com.commons.util.model.dto;

import com.commons.data.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class ResourceRegistrationRequest {
    private PersonalInformationDTO personalinformation;
    private AddressDTO address;
    private List<ProfessionalDetailsDTO> professionalinformations;
    private List<QualificationDTO> qualifications;
    private List<DocumentsDTO> documents;
    private Commission commission;
}
