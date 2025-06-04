package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DosesTxn")
@Data
public class DosesTxn extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "DosesTxn";
    private  String classname ;

    @Id
    private Long id;
    @Indexed(unique = true)
    private String pdnumber; //autogenerator
    private String presid;
    private String visitid;
    private String mmucode;

    public void setClassname(String className) {
        this.classname = "DosesTxn";
    }
}
