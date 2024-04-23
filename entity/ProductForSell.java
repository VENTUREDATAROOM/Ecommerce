package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class ProductForSell {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	//@Column(name = "product_name")
	private String productName;
	//@Column(name = "producft_sub_name")
	private String productSubName;
	//@Column(name = "product_master_code")
	private String productMasterCode;
	//@Column(name = "product_master_subcode")
	private String productMasterSubCode;
	//@Column(name = "product_final_code")
	private String productFinalCode;

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
		return productFinalCode;
	}

	public void setProductFinalCode(String productFinalCode) {
		this.productFinalCode = productFinalCode;
	}

	public ProductForSell(long id, String productName, String productSubName, String productMasterCode,
			String productMasterSubCode, String productFinalCode) {
		super();
		this.id = id;
		this.productName = productName;
		this.productSubName = productSubName;
		this.productMasterCode = productMasterCode;
		this.productMasterSubCode = productMasterSubCode;
		this.productFinalCode = productFinalCode;
	}

	public ProductForSell() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductForSell [id=" + id + ", productName=" + productName + ", productSubName=" + productSubName
				+ ", productMasterCode=" + productMasterCode + ", productMasterSubCode=" + productMasterSubCode
				+ ", productFinalCode=" + productFinalCode + "]";
	}

}
