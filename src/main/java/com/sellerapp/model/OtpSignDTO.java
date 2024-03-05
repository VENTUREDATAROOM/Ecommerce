package com.sellerapp.model;

public class OtpSignDTO {

	// private String username;
	private String email;

	public OtpSignDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
