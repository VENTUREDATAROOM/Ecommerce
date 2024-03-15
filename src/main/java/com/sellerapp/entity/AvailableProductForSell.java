package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
public class AvailableProductForSell {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String productName;
	
	private String productSubName;
	
	private String userCode;
	//@Column(name="product_master_code")
	private String productMasterCode;
//	@Column(name="product_master_sub_code")
	private String productMasterSubCode;
	//@Column(name="mandi_name")
	private String mandiName;
	//@Column(name="quantity")
	private String quantity;
	//@Column(name="address")
	private String address;
	//@Column(name="expectedPrice")
	private String expectedPrice;
	//@Column(name="price")
	private String totalPrice;
	//@Column(name="location")
	private String location;
	//@Column(name="quality")
	private String quality;
	//@Column(name="distance_from_mandi")
	private String distanceFromMandi;
	//@Column(name="order_otp")
	private String orderOtp;
//	@Column(name="order_code")
	private String orderCode;
//	@Column(name="harvest_date")
	private String harvestDate;
	//@Column(name="transaction_id")
	private String transactionId;
	
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	public String getMandiName() {
		return mandiName;
	}
	public void setMandiName(String mandiName) {
		this.mandiName = mandiName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getExpectedPrice() {
		return expectedPrice;
	}
	public void setExpectedPrice(String expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getDistanceFromMandi() {
		return distanceFromMandi;
	}
	public void setDistanceFromMandi(String distanceFromMandi) {
		this.distanceFromMandi = distanceFromMandi;
	}
	public String getOrderOtp() {
		return orderOtp;
	}
	public void setOrderOtp(String orderOtp) {
		this.orderOtp = orderOtp;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getHarvestDate() {
		return harvestDate;
	}
	public void setHarvestDate(String harvestDate) {
		this.harvestDate = harvestDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AvailableProductForSell(Long id, String productName, String productSubName, String userCode,
			String productMasterCode, String productMasterSubCode, String mandiName, String quantity, String address,
			String expectedPrice, String totalPrice, String location, String quality, String distanceFromMandi,
			String orderOtp, String orderCode, String harvestDate, String transactionId, String status) {
		super();
		this.id = id;
		this.productName = productName;
		this.productSubName = productSubName;
		this.userCode = userCode;
		this.productMasterCode = productMasterCode;
		this.productMasterSubCode = productMasterSubCode;
		this.mandiName = mandiName;
		this.quantity = quantity;
		this.address = address;
		this.expectedPrice = expectedPrice;
		this.totalPrice = totalPrice;
		this.location = location;
		this.quality = quality;
		this.distanceFromMandi = distanceFromMandi;
		this.orderOtp = orderOtp;
		this.orderCode = orderCode;
		this.harvestDate = harvestDate;
		this.transactionId = transactionId;
		this.status = status;
	}
	public AvailableProductForSell() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AvailableProductForSell [id=" + id + ", productName=" + productName + ", productSubName="
				+ productSubName + ", userCode=" + userCode + ", productMasterCode=" + productMasterCode
				+ ", productMasterSubCode=" + productMasterSubCode + ", mandiName=" + mandiName + ", quantity="
				+ quantity + ", address=" + address + ", expectedPrice=" + expectedPrice + ", totalPrice=" + totalPrice
				+ ", location=" + location + ", quality=" + quality + ", distanceFromMandi=" + distanceFromMandi
				+ ", orderOtp=" + orderOtp + ", orderCode=" + orderCode + ", harvestDate=" + harvestDate
				+ ", transactionId=" + transactionId + ", status=" + status + "]";
	}
	
	
	

}
