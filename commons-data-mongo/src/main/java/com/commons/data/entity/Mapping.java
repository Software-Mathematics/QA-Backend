package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Mapping")
@Data
public class Mapping extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Mapping";
    @Id
    private Long id;
    private String type;
    private String sub_type;
    private String name;
    private String description;
    private String mapping_id;
    private String value;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "Mapping";
    }
}
