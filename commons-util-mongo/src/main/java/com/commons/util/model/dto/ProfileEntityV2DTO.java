package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntityV2DTO extends BaseDtoI{

//    private long id;
    private String resourcecode;
    private String groupcode;
    private String rolecode;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String resetflag;
    private String token;
    private String profileid;
    private String emailid;
    private String phoneno;
    private String classname;

}
