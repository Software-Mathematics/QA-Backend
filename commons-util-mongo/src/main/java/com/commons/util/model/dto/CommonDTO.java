package com.commons.util.model.dto;

import com.commons.data.entity.ProfessionalDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class CommonDTO {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegistrationDTO registrationdto;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonalInformationDTO personalinformationdto;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommunicationRecordDTO> communicationrecorddtos;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfessionalDetails professionaldetails;

}
