package com.commons.util.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserRegistrationDTO {

    private String id;
    private String profileid;
    private String resourcecode;
    private String groupcode;
    private String rolecode;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private boolean locked = false;
    private boolean enabled = false;
    private int count = 1;
    private String classname;



    public UserRegistrationDTO(String firstName, String lastName, String userName, String password) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.username = userName;
        this.password = password;
    }
}
