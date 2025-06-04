package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StationEvent")
@Data
public class StationEvent extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "StationEvent";
    @Id
    private Long id;
    private String type;
    private String value;
    private String code;
    private String name;
}
