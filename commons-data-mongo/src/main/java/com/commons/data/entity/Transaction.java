package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transaction")
@Data
public class Transaction extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Transaction";
    private  String classname =  "Transaction";
    @Id
    private Long id;

    private String mrnumber;
    private String mmuid;
    private String whid;
    private String blockid;
    private String reqLineitemid;
    private String flowtype;
    private String referencenumber;//Autogeneration
    private String quantity;
    private String uom;
    private String sourcereference;
    private String targetreference;
    private String itemname;
    private String itemtype;
    private String mmucode;
    private String ponumber;

    public void setClassname(String classname) {
        this.classname = "Transaction";
    }
}
