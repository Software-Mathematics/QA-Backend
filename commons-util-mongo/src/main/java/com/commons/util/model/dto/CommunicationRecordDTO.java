package com.commons.util.model.dto;

import lombok.Data;
@Data
public class CommunicationRecordDTO extends BaseDtoI {
//    private Long id;
    private String profileid;
    private String name;
    private String type;
    private String value;
    private int priority;
    private String classname;
}
