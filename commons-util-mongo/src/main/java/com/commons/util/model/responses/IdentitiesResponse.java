package com.commons.util.model.responses;

import com.commons.util.model.dto.IdentitiesDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;


public class IdentitiesResponse {
    @JsonInclude(Include.NON_NULL)
    private IdentitiesDTO identitiesDTO;
    @JsonInclude(Include.NON_NULL)
    private List<IdentitiesDTO> identitiesDTOS;

    public IdentitiesDTO getIdentitiesDTO() {
        return identitiesDTO;
    }

    public void setIdentitiesDTO(IdentitiesDTO identitiesDTO) {
        this.identitiesDTO = identitiesDTO;
    }

    public List<IdentitiesDTO> getIdentitiesDTOS() {
        return identitiesDTOS;
    }

    public void setIdentitiesDTOS(List<IdentitiesDTO> identitiesDTOS) {
        this.identitiesDTOS = identitiesDTOS;
    }
}
