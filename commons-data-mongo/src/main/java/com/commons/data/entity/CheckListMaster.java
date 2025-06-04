package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CheckListMaster")
@Data
public class CheckListMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "CheckListMaster";
    @Id
    private Long id;
    private String stationcode;
    private Category category;
}
