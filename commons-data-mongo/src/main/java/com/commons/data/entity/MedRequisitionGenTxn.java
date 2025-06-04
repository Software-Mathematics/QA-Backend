package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MedRequisitionGenTxn")
@Data
public class MedRequisitionGenTxn extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "MedRequisitionGenTxn";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String mrnumber; //Autogeneration
    private String mmuid;
    private String whid;
    private String owhid;
    private String owhname;
    private String comment;
    private String blockid;
    private String mmucode;
    private String mmuname;
    private String remarks;
    private String whname;
    private String vendor;
    private String ponumber;
    private  String classname;

    public void setClassname(String classname) {
        this.classname = "MedRequisitionGenTxn";
    }
}
