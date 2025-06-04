package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Identities")
@Data
public class Identities extends BaseEntity {

	@Id
    @Transient
    public static final String SEQUENCE_NAME = "Identities";
    private  String classname ;
    private Long id;

//    @Column(name = "profileid")
    private String profileid;

//    @Column(name = "identityname")
    private String identityname;

//    @Column(name = "identityvalue")
    private String identityvalue;

//    @Column(name = "identityprovider")
    private String identityprovider;

//    @Column(name = "identityStartDate")
    private String identitystartdate;

//    @Column(name = "identityexpiredate")
    private String identityexpiredate;

    public void setClassname(String classname) {
        this.classname = "Identities";
    }


}



