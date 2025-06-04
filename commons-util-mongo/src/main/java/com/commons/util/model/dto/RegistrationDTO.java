package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO extends BaseDtoI {

//    private Long id;

	private String classname;
    private String profileid;

    private String resourcecode;

    private String groupcode;
    private String rolecode;

    private String firstname;

    private String lastname;

    private String username;
    private String password;

    private boolean locked = false;

    private boolean enabled = false;

    private int count = 1;
    

    public String getProfileId() {
		return profileid;
	}


	public void setProfileId(String profileId) {
		this.profileid = profileId;
	}


	public String getResourceCode() {
		return resourcecode;
	}


	public void setResourceCode(String resourceCode) {
		this.resourcecode = resourceCode;
	}


	public String getGroupCode() {
		return groupcode;
	}


	public void setGroupCode(String groupCode) {
		this.groupcode = groupCode;
	}


	public String getRoleCode() {
		return rolecode;
	}


	public void setRoleCode(String roleCode) {
		this.rolecode = roleCode;
	}


	public String getFirstName() {
		return firstname;
	}


	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}


	public String getLastName() {
		return lastname;
	}


	public void setLastName(String lastName) {
		this.lastname = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public RegistrationDTO(String firstName, String lastName, String userName, String resourceCode, String roleCode, String groupCode) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.username = userName;
        this.resourcecode = resourcecode;
        this.rolecode = rolecode;
        this.groupcode = groupcode;
    }
}
