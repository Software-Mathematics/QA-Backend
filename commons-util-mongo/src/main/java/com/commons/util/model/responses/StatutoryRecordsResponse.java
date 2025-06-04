package com.commons.util.model.responses;

import com.commons.util.model.dto.KycDTO;
import com.commons.util.model.dto.StatutoryRecordsDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class StatutoryRecordsResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatutoryRecordsDTO statutoryRecordsDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StatutoryRecordsDTO> statutoryRecordsDTOList;
}
