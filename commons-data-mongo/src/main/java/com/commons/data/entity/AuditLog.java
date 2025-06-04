package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AuditLog")
@Data
public class AuditLog extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "AuditLog";
    @Id
    private Long id;
    private Long classid;
    private Object object;
    private String type;
    private String sub_type;
    private String mapping_id;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "AuditLog";
    }
}
