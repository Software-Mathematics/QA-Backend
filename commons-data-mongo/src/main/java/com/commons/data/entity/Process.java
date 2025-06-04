package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Process")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Process extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Process";
    @Id
    private Long id;
    private String stationsequence;
    private String code;
    private String name;
    private String bottleneckcycletime;
    private String operatorefficiencyfactor;

}