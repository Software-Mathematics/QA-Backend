package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "CollectionSchema")
@Data
public class CollectionSchema extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "CollectionSchema";
    @Id
    private Long id;
    private String schemaname;
    private Map<String, String> schema;

    @Override
    public String toString() {
        return "CollectionSchema{" +
                "id=" + id +
                ", schemaname='" + schemaname + '\'' +
                ", schema=" + schema +
                '}';
    }
}
