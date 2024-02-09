package com.sellerapp.model;
public class OtpSignDto {
	private String userCode;
	private String username;
	private String email;

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public OtpSignDto(String userCode, String username, String email) {
		super();
		this.userCode = userCode;
		this.username = username;
		this.email = email;
	}
	public OtpSignDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	@Override
	public String toString() {
		return "OtpSignDto [userCode=" + userCode + ", username=" + username + ", email=" + email + "]";
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
