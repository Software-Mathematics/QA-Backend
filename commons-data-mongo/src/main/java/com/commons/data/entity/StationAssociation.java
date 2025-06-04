package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StationAssociation")
@Data
public class StationAssociation extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "StationAssociation";
    @Id
    private Long id;
    private String stationcode;
    private Machine machine;
}
