package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MedRequisitionGen")
@Data
public class MedRequisitionGen extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "MedRequisitionGen";
    @Id
    private Long id;
    private String code;
    private String mrnumber;
    private String mmuid;
    private String whid;
    private String owhid;
    private String owhname;
    private String blockid;
    private String mmuitemcode;
    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;
    private String uom;
    private String mmucode;
    private String mmuname;
    private String remarks;
    private String whname;
    private String name;
    private String vendor;
    private String ponumber;
    private String unitprice;
    private String netprice;
    private Itembatch itembatch;
    private  String classname ;

    public void setClassname(String classname) {
        this.classname = "MedRequisitionGen";
    }

}
