package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PartMaster")
@Data
public class PartMaster extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "PartMaster";
    @Id
    private Long id;
    private String modelid;
    private String partcode;
    private String partdescription;
    private String sfprice;
    private String customerprice;
    private String bomqty;
    private String hsn;
    private String weight;
    private String bomtype;
    private String exwarrantydays;
    private String valueapproval;
    private String safetybox;
    private String paidwarranty;
    private String isserialized;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "PartMaster";
    }
}
