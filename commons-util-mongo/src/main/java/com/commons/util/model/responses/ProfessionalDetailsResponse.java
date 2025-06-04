package com.commons.util.model.responses;

import com.commons.util.model.dto.KycDTO;
import com.commons.util.model.dto.ProfessionalDetailsDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionalDetailsResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfessionalDetailsDTO professionalDetailsDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProfessionalDetailsDTO> professionalDetailsDTOList;
}
