package com.commons.util.model.responses;

import com.commons.data.entity.CommunicationRecord;
import com.commons.data.entity.PersonalInformation;
import com.commons.data.entity.ProfessionalDetails;
import com.commons.data.entity.Registration;
import com.commons.util.model.dto.CommunicationRecordDTO;
import com.commons.util.model.dto.PersonalInformationDTO;
import com.commons.util.model.dto.RegistrationDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class CommonDTOResponse {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileId;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Registration registration;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PersonalInformation personalInformation;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommunicationRecord> communicationRecord;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfessionalDetails professionalDetails;
}
