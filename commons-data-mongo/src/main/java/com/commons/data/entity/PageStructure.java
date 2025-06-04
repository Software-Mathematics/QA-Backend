package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "PageStructure")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageStructure extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "PageStructure";
    private  String classname;
    @Id
    private Long id;
    private String tagName;
    private String attributeName;
    private String attributeValue;
    private List<PageStructure> children;

    public void setClassname(String classname) {
        this.classname = "PageStructure";
    }

}
