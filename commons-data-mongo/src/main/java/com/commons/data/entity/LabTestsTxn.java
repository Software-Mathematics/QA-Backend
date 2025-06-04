package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "LabTestsTxn")
@Data
public class LabTestsTxn extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "LabTestsTxn";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String ptnumber; //autogeneration
    private String presid;
    private String visitid;
    private String mmucode;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "LabTestsTxn";
    }
}
