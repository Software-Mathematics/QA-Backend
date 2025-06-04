package com.commons.util.model.responses;

import com.commons.util.model.dto.ExperienceDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class ExperienceResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExperienceDTO experienceDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ExperienceDTO> experienceDTOList;
}
