package com.commons.util.model.responses;

import com.commons.util.model.dto.CommonDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetAllDTO {
    private List<CommonDTOResponse> commonDTOResponses;
}
