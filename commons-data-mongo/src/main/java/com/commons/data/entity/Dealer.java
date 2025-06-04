package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Dealer")
@Data
public class Dealer extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Dealer";
    @Id
    private Long id;
    private String dealer;
    private String name;
    private String mobileno;
    private String pincode;
    private String profileid;
}
