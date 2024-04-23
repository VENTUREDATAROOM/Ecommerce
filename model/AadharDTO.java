package com.sellerapp.model;



import org.springframework.web.multipart.MultipartFile;

public class AadharDTO  {
	
	private String aadharNumber;
	private MultipartFile frontPage;
	private MultipartFile backPage;
	private String  baseFront;
	private String baseBack;
	private String userCode;

	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public AadharDTO(String aadharNumber, MultipartFile frontPage, MultipartFile backPage, String baseFront,
			String baseBack, String userCode) {
		super();
		this.aadharNumber = aadharNumber;
		this.frontPage = frontPage;
		this.backPage = backPage;
		this.baseFront = baseFront;
		this.baseBack = baseBack;
		this.userCode = userCode;
	}

	public String getBaseFront() {
		return baseFront;
	}

	public void setBaseFront(String baseFront) {
		this.baseFront = baseFront;
	}

	public String getBaseBack() {
		return baseBack;
	}

	public void setBaseBack(String baseBack) {
		this.baseBack = baseBack;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public MultipartFile getFrontPage() {
		return frontPage;
	}

	public void setFrontPage(MultipartFile frontPage) {
		this.frontPage = frontPage;
	}

	public MultipartFile getBackPage() {
		return backPage;
	}

	public void setBackPage(MultipartFile backPage) {
		this.backPage = backPage;
	}

	
	

	
	

	
	

	
	
	
}
