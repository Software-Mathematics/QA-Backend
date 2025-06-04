package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Persistence")
@Data
public class Persistence  extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Persistence";
    @Id
    private Long id;
    private Map<String, Object> object;
}
