package com.sellerapp.model;



public class JwtWithUserCodeResponse {
	
	private String userCode;
	private String token;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JwtWithUserCodeResponse(String userCode, String token) {
		super();
		this.userCode = userCode;
		this.token = token;
	}
	public JwtWithUserCodeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JwtWithUserCodeResponse [userCode=" + userCode + ", token=" + token + "]";
	}
	
	
	
	

}
