package com.commons.util.model.dto;

import lombok.Data;

@Data
public class ForgetPasswordDTO {
    private String username;
    private String resourcecode;
    private String newpassword;
    private String type;
}