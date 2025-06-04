package com.commons.util.model.responses;

import com.commons.util.model.dto.CommonDTO;
import com.commons.util.model.dto.RegistrationDTO;
import com.commons.util.model.dto.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
public class UserRegistrationResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserRegistrationDTO userRegistrationDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserRegistrationDTO> userRegistrationDTOS;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CommonDTO commonDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegistrationDTO registrationDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RegistrationDTO> registrationDTOS;


    public UserRegistrationDTO getUserRegistrationDTO() {
        return userRegistrationDTO;
    }

    public void setUserRegistrationDTO(UserRegistrationDTO userRegistrationDTO) {
        this.userRegistrationDTO = userRegistrationDTO;
    }

    public List<UserRegistrationDTO> getUserRegistrationDTOS() {
        return userRegistrationDTOS;
    }

    public void setUserRegistrationDTOS(List<UserRegistrationDTO> userRegistrationDTOS) {
        this.userRegistrationDTOS = userRegistrationDTOS;
    }
}
