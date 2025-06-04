package com.commons.util.model;

import com.commons.util.model.PersonalInformationDTO;

public class ProfileDTO {

    private String id;
    private PersonalInformationDTO personalInformation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalInformationDTO getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformationDTO personalInformation) {
        this.personalInformation = personalInformation;
    }
}
