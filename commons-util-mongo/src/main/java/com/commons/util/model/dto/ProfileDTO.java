package com.commons.util.model.dto;

public class ProfileDTO {

    private String id;
    private PersonalInformationDTO personalinformation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalInformationDTO getPersonalInformation() {
        return personalinformation;
    }

    public void setPersonalInformation(PersonalInformationDTO personalinformation) {
        this.personalinformation = personalinformation;
    }
}
