package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CheckListHeader")
@Data
public class CheckListHeader extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "CheckListHeader";
    @Id
    private Long id;
    private String operatorname;
    private String operatorid;
    private String code;
    private String stationname;
    private String stationcode;
    private String type;
}
