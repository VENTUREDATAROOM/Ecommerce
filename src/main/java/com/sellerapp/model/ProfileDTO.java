package com.sellerapp.model;

import org.springframework.web.multipart.MultipartFile;

public class ProfileDTO {

	private String dateofbirth;
	private MultipartFile profilePic;
	private String baseProfile;
	private String userCode;
	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public MultipartFile getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(MultipartFile profilePic) {
		this.profilePic = profilePic;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getBaseProfile() {
		return baseProfile;
	}

	public void setBaseProfile(String baseProfile) {
		this.baseProfile = baseProfile;
	}

}