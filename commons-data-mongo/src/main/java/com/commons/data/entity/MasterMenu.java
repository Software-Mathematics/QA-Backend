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
@Document(collection = "mastermenu")
public class MasterMenu {
    @Transient
    public static final String SEQUENCE_NAME = "MasterMenu";
    @Id
    private Long menuid;
    private String state;
    private String name;
    private String type;
    private String icon;
    private String role;
    private String landingurl;
    private String resourcecode;
    private  String classname ;

    public void setClassname(String classname) {
        this.classname = "MasterMenu";
    }

}
