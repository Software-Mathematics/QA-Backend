package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Price")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Price";
    @Id
    private Long id;
    private String code;
    private String classname;
    private String type;
    private Address address;
    private Qualification qualification;
    private String price;
    private String profileid;
    private String name;
    private String itemcode;
    private String description;
    private String operationtype;
    private String valuetype;
    private String value;
    private String currency;
    private String uom;
    private String unit;
    private String taxinclusiveflag;
    private String pricename;
    private String mmucode;

    public void setClassname(String classname) {  this.classname = "Price";    }

}
