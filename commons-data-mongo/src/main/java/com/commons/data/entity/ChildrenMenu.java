package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "ChildrenMenu")
public class ChildrenMenu {

    @Transient
    public static final String SEQUENCE_NAME = "ChildrenMenu";

    @Id
    private Long childrenid;
    private String state;
    private String name;
    private String type;
    private Long mastermenuid;
    private  String classname ;
    public void setClassname(String classname) {
        this.classname = "ChildrenMenu";
    }
}
