package com.commons.util.model.responses;

import com.commons.util.model.dto.CommunicationRecordDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;


public class CommunicationRecordResponse {
    @JsonInclude(Include.NON_NULL)
    private CommunicationRecordDTO communicationRecordDTO;
    @JsonInclude(Include.NON_NULL)
    private List<CommunicationRecordDTO> communicationRecordDTOS;

    public CommunicationRecordDTO getCommunicationRecordDTO() {
        return communicationRecordDTO;
    }

    public void setCommunicationRecordDTO(CommunicationRecordDTO communicationRecordDTO) {
        this.communicationRecordDTO = communicationRecordDTO;
    }

    public List<CommunicationRecordDTO> getCommunicationRecordDTOS() {
        return communicationRecordDTOS;
    }

    public void setCommunicationRecordDTOS(List<CommunicationRecordDTO> communicationRecordDTOS) {
        this.communicationRecordDTOS = communicationRecordDTOS;
    }
}
