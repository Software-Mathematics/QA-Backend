package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DataModel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataModel extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "DataModel";
    private  String classname;
    @Id
    private Long id;
    private String field;
    private String datatype;
    private String validation;

    public void setClassname(String classname) {
        this.classname = "DataModel";
    }

}
