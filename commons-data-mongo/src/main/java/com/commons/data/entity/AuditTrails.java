package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AuditTrails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditTrails {
    @Id
    private String audit_id;
    private String acter;
    private String name;
    private String value;
    //String entity_name;

}
