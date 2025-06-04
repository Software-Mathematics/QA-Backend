package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ConsentEntry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentEntry extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ConsentEntry";
    private  String classname;
    @Id
    private Long id;
    private String profileid;
    private String consent_master_id;
    private String is_consented;
    private String consent_date;
    private ConsentSource consent_source;
    private String remarks;
    private String resourcecode;


    public void setClassname(String classname) {
        this.classname = "ConsentEntry";
    }

}
