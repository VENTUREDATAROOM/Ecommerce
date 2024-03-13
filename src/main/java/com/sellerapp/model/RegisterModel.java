package com.sellerapp.model;

import org.springframework.web.multipart.MultipartFile;

public class RegisterModel {

	private MultipartFile profileImage;
	private String mobileNumber;
	private String name;
	private String email;
	private String password;
	private String baseImg;
	private String contentType;
	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getBaseImg() {
		return baseImg;
	}

	public void setBaseImg(String baseImg) {
		this.baseImg = baseImg;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public RegisterModel(MultipartFile profileImage, String mobileNumber, String name, String email, String password,
			String baseImg,String otp, String contentType) {
		super();
		this.profileImage = profileImage;
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.baseImg = baseImg;
		this.otp=otp;
		this.contentType = contentType;
	}

	public RegisterModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
