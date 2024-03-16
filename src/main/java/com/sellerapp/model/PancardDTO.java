package com.sellerapp.model;

import org.springframework.web.multipart.MultipartFile;

public class PancardDTO {

	private String pancardNumber;
	private MultipartFile pic;
	private String basePan;
	private String userCode;

	public String getPancardNumber() {
		return pancardNumber;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public MultipartFile getPic() {
		return pic;
	}

	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}

	public String getBasePan() {
		return basePan;
	}

	public void setBasePan(String basePan) {
		this.basePan = basePan;
	}

}