package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OTP_DTO {

    private String id;
    private int otp;
    private String userid;
    private String phonenumber;
    private LocalDateTime createdat;
    private LocalDateTime expiresat;
    private LocalDateTime confirmedat;
    private RegistrationDTO user;
	private String classname;
    private int count = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getUserId() {
		return userid;
	}
	public void setUserId(String userid) {
		this.userid = userid;
	}
	public String getPhoneNumber() {
		return phonenumber;
	}
	public void setPhoneNumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public LocalDateTime getCreatedAt() {
		return createdat;
	}
	public void setCreatedAt(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	public LocalDateTime getExpiresAt() {
		return expiresat;
	}
	public void setExpiresAt(LocalDateTime expiresat) {
		this.expiresat = expiresat;
	}
	public LocalDateTime getConfirmedAt() {
		return confirmedat;
	}
	public void setConfirmedAt(LocalDateTime confirmedat) {
		this.confirmedat = confirmedat;
	}
	public RegistrationDTO getUser() {
		return user;
	}
	public void setUser(RegistrationDTO user) {
		this.user = user;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
    


}
