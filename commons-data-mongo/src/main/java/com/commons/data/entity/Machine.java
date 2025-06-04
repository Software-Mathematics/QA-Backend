package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Machine")
@Data
public class Machine extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Machine";
    @Id
    private Long id;
    private String code;
    private String name;
    private String description;
    private String document;
    private String type;
    private String hierarchicalcode;
    private String parentcode;
    private String parenthierarchicalcode;
}
