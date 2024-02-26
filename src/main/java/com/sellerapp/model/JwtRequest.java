package com.sellerapp.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String mobileNumber;
	private String password;
    private String otpgen;

	

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JwtRequest() {

	}
	
	
	

	public String getOtpgen() {
		return otpgen;
	}

	public void setOtpgen(String otpgen) {
		this.otpgen = otpgen;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JwtRequest(String mobileNumber, String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	
}
