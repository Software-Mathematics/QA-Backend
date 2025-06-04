package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "GSTMaster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GSTMaster extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "GSTMaster";
    @Id
    private Long id;
    private String code;
    private String sno;
    private String heading;
    private String type;
    private String description;
    private String schedules;
    private String condition;
    private String compensation;
    private String cgst;
    private String sgst;
    private String igst;

}



