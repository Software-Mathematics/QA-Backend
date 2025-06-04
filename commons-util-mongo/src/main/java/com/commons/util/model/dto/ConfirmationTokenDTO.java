package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ConfirmationTokenDTO {


    private String id;
    private String token;
    private LocalDateTime createdat;
    private LocalDateTime expiresat;
    private LocalDateTime confirmedat;
    private RegistrationDTO registration;
    private String classname;

    public ConfirmationTokenDTO() {
    }

    public ConfirmationTokenDTO(String token, LocalDateTime createdAt, LocalDateTime expiresAt, RegistrationDTO registration) {
        this.token = token;
        this.createdat = createdAt;
        this.expiresat = expiresAt;
        this.registration = registration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(LocalDateTime createdAt) {
        this.createdat = createdAt;
    }

    public LocalDateTime getExpiresat() {
        return expiresat;
    }

    public void setExpiresat(LocalDateTime expiresAt) {
        this.expiresat = expiresAt;
    }

    public LocalDateTime getConfirmedat() {
        return confirmedat;
    }

    public void setConfirmedat(LocalDateTime confirmedAt) {
        this.confirmedat = confirmedAt;
    }


}
