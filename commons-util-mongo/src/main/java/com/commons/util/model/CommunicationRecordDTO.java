package com.commons.util.model;

import com.commons.util.model.dto.BaseDTO;
import lombok.Data;

@Data
public class CommunicationRecordDTO {
    private String id;
    private String profileId;
    private String name;
    private String type;
    private String value;
    private Boolean status;
    private int priority;
    private BaseDTO auditTrails;
}
