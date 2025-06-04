package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DoseGap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoseGap extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "DoseGap";
    private  String classname;
    @Id
    private Long id;
    private String dose_no;
    private String gap;


    public void setClassname(String classname) {
        this.classname = "DoseGap";
    }

}