package com.sellerapp.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String mobileNumber;
	private String password;
	private String otpgen;
	private String name;
	@JsonIgnore
	private MultipartFile profileImage;
	private String baseImg;

	private String contentType;

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public String getBaseImg() {
		return baseImg;
	}

	public void setBaseImg(String baseImg) {
		this.baseImg = baseImg;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtRequest(String mobileNumber, String password, String otpgen, String name, MultipartFile profileImage,
			String baseImg, String contentType) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.otpgen = otpgen;
		this.name = name;
		this.profileImage = profileImage;
		this.baseImg = baseImg;
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "JwtRequest [mobileNumber=" + mobileNumber + ", password=" + password + ", otpgen=" + otpgen + ", name="
				+ name + ", profileImage=" + profileImage + ", baseImg=" + baseImg + ", contentType=" + contentType
				+ "]";
	}

}
