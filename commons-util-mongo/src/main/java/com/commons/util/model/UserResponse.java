package com.commons.util.model;

//import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse{
	@JsonInclude(Include.NON_NULL)
	private UserDTO userDetails;
	@JsonInclude(Include.NON_NULL)
	private List<UserDTO> userDetailsList;


	public UserDTO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDTO userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserDTO> getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(List<UserDTO> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}


}
