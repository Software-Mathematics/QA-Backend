package com.commons.util.model.dto;

public class UserLoginDTO {
    private String userid;
    private String password;


    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
