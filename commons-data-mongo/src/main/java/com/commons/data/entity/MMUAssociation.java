package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MMUAssociation")
@Data
public class MMUAssociation extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "MMUAssociation";
    @Id
    private Long id;
    private String code;
    private String profileid;
    private String mmucode;
    private String rolecode;
    private String fullname;
    private String firstname;
    private String lastname;
    private String rolename;
    private String mmuname;
    private String username;
    private String parentcode;
    private String type;
    private String parentHierarchicalcode;
    private  String classname ;

    public void setClassname(String classname) {
        this.classname = "MMUAssociation";
    }
}
