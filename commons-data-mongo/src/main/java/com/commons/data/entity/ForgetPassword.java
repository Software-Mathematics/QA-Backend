package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ForgetPassword")
@Data
public class ForgetPassword{
    private String username;
    private String resourcecode;
    private String newpassword;
}