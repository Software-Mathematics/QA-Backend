package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FrequencyMaster")
@Data
public class FrequencyMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "FrequencyMaster";
    @Id
    private Long id;
    private String code;
    private String description;
    private String quantity;
    private  String classname;
    private String countperday;
    public void setClassname(String classname) {
        this.classname = "FrequencyMaster";
    }
}
