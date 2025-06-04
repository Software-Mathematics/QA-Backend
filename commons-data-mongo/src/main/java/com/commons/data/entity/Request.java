package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Request")
@Data
public class Request extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Request";
    @Id
    private Long id;
    private String profileid;
    private String type;
    private Map<String,Object> object;
}
