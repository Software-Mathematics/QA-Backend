package com.commons.util.model;

import com.commons.util.model.responses.PersonalInformationResponse;
import com.commons.util.model.ProfileResponse;
import com.fasterxml.jackson.annotation.JsonInclude;


public class ResponseEntities {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    AppResponseDTO<String> stringAppResponseDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    AppResponseDTO<ProfileResponse> profileResponseAppResponseDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    AppResponseDTO<PersonalInformationResponse> personalInformationResponseAppResponseDTO;

    public AppResponseDTO<PersonalInformationResponse> getPersonalInformationResponseAppResponseDTO() {
        return personalInformationResponseAppResponseDTO;
    }

    public void setPersonalInformationResponseAppResponseDTO(AppResponseDTO<PersonalInformationResponse> personalInformationResponseAppResponseDTO) {
        this.personalInformationResponseAppResponseDTO = personalInformationResponseAppResponseDTO;
    }

    public AppResponseDTO<ProfileResponse> getProfileResponseAppResponseDTO() {
        return profileResponseAppResponseDTO;
    }

    public void setProfileResponseAppResponseDTO(AppResponseDTO<ProfileResponse> profileResponseAppResponseDTO) {
        this.profileResponseAppResponseDTO = profileResponseAppResponseDTO;
    }

    public AppResponseDTO<String> getStringAppResponseDTO() {
        return stringAppResponseDTO;
    }

    public void setStringAppResponseDTO(AppResponseDTO<String> stringAppResponseDTO) {
        this.stringAppResponseDTO = stringAppResponseDTO;
    }
}
