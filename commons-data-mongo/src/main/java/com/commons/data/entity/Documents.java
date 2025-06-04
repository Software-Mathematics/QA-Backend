package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documents extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "Documents";
    private  String classname ;
    @Id
    private Long id;

    private String profileid;
    private String name;
    private String description;

    public void setClassname(String classname) {
        this.classname = "Documents";
    }

}
