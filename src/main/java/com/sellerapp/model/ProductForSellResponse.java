package com.sellerapp.model;

public class ProductForSellResponse {

	private long id;
	private String productName;
	private String productSubName;
	private String productMasterCode;
	private String productMasterSubCode;
	private String ProductFinalCode;
	private String productImage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSubName() {
		return productSubName;
	}

	public void setProductSubName(String productSubName) {
		this.productSubName = productSubName;
	}

	public String getProductMasterCode() {
		return productMasterCode;
	}

	public void setProductMasterCode(String productMasterCode) {
		this.productMasterCode = productMasterCode;
	}

	public String getProductMasterSubCode() {
		return productMasterSubCode;
	}

	public void setProductMasterSubCode(String productMasterSubCode) {
		this.productMasterSubCode = productMasterSubCode;
	}

	public String getProductFinalCode() {
		return ProductFinalCode;
	}

	public void setProductFinalCode(String productFinalCode) {
		ProductFinalCode = productFinalCode;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public ProductForSellResponse(long id, String productName, String productSubName, String productMasterCode,
			String productMasterSubCode, String productFinalCode, String productImage) {
		super();
		this.id = id;
		this.productName = productName;
		this.productSubName = productSubName;
		this.productMasterCode = productMasterCode;
		this.productMasterSubCode = productMasterSubCode;
		ProductFinalCode = productFinalCode;
		this.productImage = productImage;
	}

	public ProductForSellResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductForSellResponse [id=" + id + ", productName=" + productName + ", productSubName="
				+ productSubName + ", productMasterCode=" + productMasterCode + ", productMasterSubCode="
				+ productMasterSubCode + ", ProductFinalCode=" + ProductFinalCode + ", productImage=" + productImage
				+ "]";
	}

}
