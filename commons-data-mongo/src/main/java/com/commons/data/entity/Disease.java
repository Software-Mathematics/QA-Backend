package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Disease")
public class Disease extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Disease";
    private  String classname ;
    @Id
    private Long id;
    private String name;
    private String type;
    private String category;
    private String code;
    private String description;
    private String resourcecode;


    public void setClassName(String className) {
        this.classname = "Disease";
    }
}
