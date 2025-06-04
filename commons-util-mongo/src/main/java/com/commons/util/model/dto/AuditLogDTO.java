package com.commons.util.model.dto;

import lombok.Data;

@Data
public class AuditLogDTO extends BaseDtoI {
    private Long classid;
    private Object object;
    private String type;
    private String sub_type;
    private String mapping_id;
    private String classname;
}
