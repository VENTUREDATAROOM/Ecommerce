package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="product_details")
public class ProductEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_seq")
	@Column(name="product_id")
	private Long productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_subname")
	private String productSubName;
	@Column(name="product_code")
	private String productCode;
	@Column(name="product_subcode")
	private String productSubCode;
	@Column(name="product_finalcode")
	private String productFinalCode;
	@OneToOne(mappedBy="product")
	private ProductImageEntity productImage;
	
	
	
	public ProductImageEntity getProductImage() {
		return productImage;
	}
	public void setProductImage(ProductImageEntity productImage) {
		this.productImage = productImage;
	}
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
	public String getProductSubName() {
		return productSubName;
	}
	public void setProductSubName(String productSubName) {
		this.productSubName = productSubName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
