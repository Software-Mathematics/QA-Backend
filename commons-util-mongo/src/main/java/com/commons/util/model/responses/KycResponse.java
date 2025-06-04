package com.commons.util.model.responses;

import com.commons.util.model.dto.KycDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class KycResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private KycDTO kycDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<KycDTO> kycDTOList;
}
