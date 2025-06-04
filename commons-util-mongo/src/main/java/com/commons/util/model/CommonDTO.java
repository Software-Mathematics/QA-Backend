package com.commons.util.model;

import com.commons.data.entity.ProfessionalDetails;
import com.commons.util.model.dto.RegistrationDTO;
import lombok.Data;

import java.util.List;

@Data
public class CommonDTO {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegistrationDTO registrationDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonalInformationDTO personalInformationDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommunicationRecordDTO> communicationRecordDTOS;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfessionalDetails professionalDetails;

}
