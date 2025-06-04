package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Referal")
@Data
public class Referal extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Referal";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String referalid; //autogeneration
    private String patientid;
    private String mmucode;
    private String doctor;
    private String priortreatment;
    private String reasonforreferal;
    private String presentssymptoms;
    private String additionalnotes;
    private String visitid;
    private String presid;
    private  String classname;

    public void setClassName(String className) {
        this.classname = "Referal";
    }
}
