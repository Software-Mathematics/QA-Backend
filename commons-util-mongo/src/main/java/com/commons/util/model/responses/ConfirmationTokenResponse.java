package com.commons.util.model.responses;

import com.commons.util.model.dto.ConfirmationTokenDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ConfirmationTokenResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfirmationTokenDTO confirmationTokenDTO;

    public ConfirmationTokenDTO getConfirmationTokenDTO() {
        return confirmationTokenDTO;
    }

    public void setConfirmationTokenDTO(ConfirmationTokenDTO confirmationTokenDTO) {
        this.confirmationTokenDTO = confirmationTokenDTO;
    }
}
