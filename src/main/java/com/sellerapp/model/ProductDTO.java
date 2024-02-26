package com.sellerapp.model;

public class ProductDTO {

	
	private Long productId;
	private String productName;
	private String productSubName;
	private String productCode;
	private String productSubCode;
	private String productFinalCode;
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
	public String getProductSubName() {
		return productSubName;
	}
	public void setProductSubName(String productSubName) {
		this.productSubName = productSubName;
	}
	public String getProductSubCode() {
		return productSubCode;
	}
	public void setProductSubCode(String productSubCode) {
		this.productSubCode = productSubCode;
	}
	public String getProductFinalCode() {
		return productFinalCode;
	}
	public void setProductFinalCode(String productFinalCode) {
		this.productFinalCode = productFinalCode;
	}
	
	
}
