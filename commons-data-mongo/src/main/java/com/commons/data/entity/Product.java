package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
@Data
public class Product extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Product";
    @Id
    private Long id;
    private String serialnumber;
    private Category brand;
    private Category product;
    private Category model;
    private String warrantyinmonth;
    private Boolean iswarranty;
    private Boolean isextendedwarranty;
    private String extendedwarrantyinmonth;
    private String profileid;

}
