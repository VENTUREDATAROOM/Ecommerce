package com.sellerapp.model;
public class OtpSignDTO {
	
	private String username;
	private String email;

	
	public OtpSignDTO( String username, String email) {
		super();
		
		this.username = username;
		this.email = email;
	}
	public OtpSignDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
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
