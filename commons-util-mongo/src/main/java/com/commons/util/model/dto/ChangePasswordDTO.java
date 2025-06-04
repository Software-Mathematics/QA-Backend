package com.commons.util.model.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String username;
    private String resourcecode;
    private String oldpassword;
    private String newpassword;

}
