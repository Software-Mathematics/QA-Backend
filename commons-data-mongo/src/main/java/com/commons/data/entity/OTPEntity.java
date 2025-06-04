package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "OTP")
@Data
public class OTPEntity {

    @Id
    private String id;
	private  String classname;

//    @Column(name = "otp")
    private int otp;

//    @Column(name = "userid")
    private String userid;

//    @Column(name = "phonenumber")
    private String phonenumber;

//    @Column(name = "createdat")
    private LocalDateTime createdat;

//    @Column(name = "expiresat")
    private LocalDateTime expiresat;

//    @Column(name = "confirmedat")
    private LocalDateTime confirmedat;

//    @OneToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_ids", referencedColumnName = "id")
    private Registration user;

//    @Column(name = "count")
    private int count = 0;
	public void setClassname(String classname) {
		this.classname = "OTPEntity";
	}

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

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdat = createdat;
	}

	public LocalDateTime getExpiresAt() {
		return expiresat;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresat = expiresat;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedat;
	}

	public void setConfirmedAt(LocalDateTime confirmedat) {
		this.confirmedat = confirmedat;
	}

	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    

}
