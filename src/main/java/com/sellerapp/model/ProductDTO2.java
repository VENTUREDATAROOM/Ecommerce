package com.sellerapp.model;

public class ProductDTO2 {

	private Long productId;
	private String productName;
	private String productCode;
	// private MultipartFile imgData;
	private String profileBase64Image;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProfileBase64Image() {
		return profileBase64Image;
	}

	public void setProfileBase64Image(String profileBase64Image) {
		this.profileBase64Image = profileBase64Image;
	}

}
