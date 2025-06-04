package com.commons.util.model.responses;

import com.commons.util.model.dto.PersonalInformationDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

public class PersonalInformationResponse {
    @JsonInclude(Include.NON_NULL)
    private PersonalInformationDTO personalInformationDTO;
    @JsonInclude(Include.NON_NULL)
    List<PersonalInformationDTO> personalInformationDTOS;

    public PersonalInformationDTO getPersonalInformationDTO() {
        return personalInformationDTO;
    }

    public void setPersonalInformationDTO(PersonalInformationDTO personalInformationDTO) {
        this.personalInformationDTO = personalInformationDTO;
    }

    public List<PersonalInformationDTO> getPersonalInformationDTOS() {
        return personalInformationDTOS;
    }

    public void setPersonalInformationDTOS(List<PersonalInformationDTO> personalInformationDTOS) {
        this.personalInformationDTOS = personalInformationDTOS;
    }
}
