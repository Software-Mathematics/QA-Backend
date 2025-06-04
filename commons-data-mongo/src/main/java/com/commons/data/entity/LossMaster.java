package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LossMaster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LossMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "LossMaster";
    private  String classname;
    @Id
    private Long id;
    private String lose_type;
    private Object value;
    private String updateddate;


    public void setClassname(String classname) {
        this.classname = "LossMaster";
    }

}