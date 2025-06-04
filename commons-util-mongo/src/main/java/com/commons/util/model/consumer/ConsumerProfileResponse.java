package com.commons.util.model.consumer;

import com.commons.data.entity.Doses;
import com.commons.data.entity.PinCode;
import com.commons.util.model.dto.*;
import lombok.Data;

import java.util.List;

@Data
public class ConsumerProfileResponse extends ConsumerBaseResponse {
    private String resourcecode;
    private String groupcode;
    private String rolecode;
    private String rolename;
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
    private PinCode pincode;
    private String designationcode;
    private String designationname;
    private String departmentcode;
    private String departmentname;


}
