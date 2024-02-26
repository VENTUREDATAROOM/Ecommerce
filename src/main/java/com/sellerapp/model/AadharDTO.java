package com.sellerapp.model;



import org.springframework.web.multipart.MultipartFile;

public class AadharDTO  {
	
	private String aadharNumber;
	private MultipartFile frontPage;
	private MultipartFile backPage;

	
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
