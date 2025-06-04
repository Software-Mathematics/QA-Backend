package com.commons.util.model.responses;

import com.commons.util.model.dto.ProfileEntityDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ProfileEntityResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfileEntityDTO dto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProfileEntityDTO> dtoList;
}
