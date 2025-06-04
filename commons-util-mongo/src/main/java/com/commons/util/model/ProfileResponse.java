package com.commons.util.model;

import com.commons.util.model.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ProfileResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfileDTO profileDTO;

    public ProfileDTO getProfileDTO() {
        return profileDTO;
    }

    public void setProfileDTO(ProfileDTO profileDTO) {
        this.profileDTO = profileDTO;
    }
}
