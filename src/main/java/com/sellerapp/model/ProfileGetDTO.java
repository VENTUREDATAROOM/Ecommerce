package com.sellerapp.model;

public class ProfileGetDTO {
	
	private String name;
	private String email;
	private String userCode;
	private String dateOfBirth;
	private String image;
	private String aadharNumber;
	private String panCardNumber;
	private String mobileNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanCardNumber() {
		return panCardNumber;
	}
	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public ProfileGetDTO(String name, String email, String userCode, String dateOfBirth, String image,
			String aadharNumber, String panCardNumber, String mobileNumber) {
		super();
		this.name = name;
		this.email = email;
		this.userCode = userCode;
		this.dateOfBirth = dateOfBirth;
		this.image = image;
		this.aadharNumber = aadharNumber;
		this.panCardNumber = panCardNumber;
		this.mobileNumber = mobileNumber;
	}
	public ProfileGetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfileGetDTO [name=" + name + ", email=" + email + ", userCode=" + userCode + ", dateOfBirth="
				+ dateOfBirth + ", image=" + image + ", aadharNumber=" + aadharNumber + ", panCardNumber="
				+ panCardNumber + ", mobileNumber=" + mobileNumber + "]";
	}
	
	
	

}
