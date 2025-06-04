package com.commons.util.model.responses;

import com.commons.util.model.dto.QualificationDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import java.util.List;

public class QualificationResponse {
    @JsonInclude(Include.NON_NULL)
    private QualificationDTO qualificationDTO;
    @JsonInclude(Include.NON_NULL)
    private List<QualificationDTO> qualificationDTOS;

    public QualificationDTO getQualificationDTO() {
        return qualificationDTO;
    }

    public void setQualificationDTO(QualificationDTO qualificationDTO) {
        this.qualificationDTO = qualificationDTO;
    }

    public List<QualificationDTO> getQualificationDTOS() {
        return qualificationDTOS;
    }

    public void setQualificationDTOS(List<QualificationDTO> qualificationDTOS) {
        this.qualificationDTOS = qualificationDTOS;
    }
}
