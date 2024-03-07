package com.sellerapp.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductForSellPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String productMasterSubCode;
	private String productMasterCode;
	private byte[] productImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductMasterSubCode() {
		return productMasterSubCode;
	}

	public void setProductMasterSubCode(String productMasterSubCode) {
		this.productMasterSubCode = productMasterSubCode;
	}

	public String getProductMasterCode() {
		return productMasterCode;
	}

	public void setProductMasterCode(String productMasterCode) {
		this.productMasterCode = productMasterCode;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "ProductForSellPhoto [id=" + id + ", productMasterSubCode=" + productMasterSubCode
				+ ", productMasterCode=" + productMasterCode + ", productImage=" + Arrays.toString(productImage) + "]";
	}

	public ProductForSellPhoto(Long id, String productMasterSubCode, String productMasterCode, byte[] productImage) {
		super();
		this.id = id;
		this.productMasterSubCode = productMasterSubCode;
		this.productMasterCode = productMasterCode;
		this.productImage = productImage;
	}

	public ProductForSellPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
