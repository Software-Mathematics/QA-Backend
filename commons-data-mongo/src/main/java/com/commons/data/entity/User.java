package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
@Data
public class User {

    @Id
    private String id;

//    @Column(name = "profileid")
    private String profileid;

//    @Column(name = "resourcecode")
    private String resourcecode;

    private String groupcode;
    private String rolecode;

//    @Column(name = "firstname")
    private String firstname;

//    @Column(name = "lastname")
    private String lastname;

//    @Column(name = "username")
    private String username;
//
//    @Column(name = "password")
    private String password;


//    @Column(name = "locked")
    private boolean locked = false;

//    @Column(name = "enabled")
    private boolean enabled = false;

//    @Column(name = "count")
    private int count = 1;

}
