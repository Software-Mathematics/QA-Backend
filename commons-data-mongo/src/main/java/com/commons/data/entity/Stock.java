package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Stock")
@Data
public class Stock extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Stock";
    private String classname ;
    @Id
    private Long id;
    @Indexed(unique = true)
    private String stockreference; //Autogeneration
    private String mmuid;
    private String whid;
    private String blockid;
    private String mmuitemcode;
    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;
    private String uom;
    private String name;
    private String description;
    private String openingstock;//-
    private String closingstock;//-
    private String currentstock;//-
    private String mmucode;
    private String blockname;
    private String whname;
    private String mmuname;
    private String itemcode;
    private String quantityindemand;
    private String consumption;//+


    public void setClassname(String classname) {
        this.classname = "Stock";
    }
}
