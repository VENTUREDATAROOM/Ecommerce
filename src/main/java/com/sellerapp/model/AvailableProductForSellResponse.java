package com.sellerapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class AvailableProductForSellResponse {

	private String productName;
	private String productSubName;
	private String userCode;
	private String productMasterCode;
	private String ProductMasterSubCode;
	private String mandiName;
	private String quantity;
	private String expectedPrice;
	private String totalPrice;
	private String location;
	private String quality;
	private String distanceFromMandi;
	private String orderOtp;
	private String orderCode;
	private String harvestData;
	private LocalDateTime transactionId;
	private List<String> imageData;

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
		return ProductMasterSubCode;
	}

	public void setProductMasterSubCode(String productMasterSubCode) {
		ProductMasterSubCode = productMasterSubCode;
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

	public String getHarvestData() {
		return harvestData;
	}

	public void setHarvestData(String harvestData) {
		this.harvestData = harvestData;
	}

	public LocalDateTime getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(LocalDateTime transactionId) {
		this.transactionId = transactionId;
	}

	public List<String> getImageData() {
		return imageData;
	}

	public void setImageData(List<String> imageData) {
		this.imageData = imageData;
	}

	public AvailableProductForSellResponse(String productName, String productSubName, String userCode,
			String productMasterCode, String productMasterSubCode, String mandiName, String quantity,
			String expectedPrice, String totalPrice, String location, String quality, String distanceFromMandi,
			String orderOtp, String orderCode, String harvestData, LocalDateTime transactionId,
			List<String> imageData) {
		super();
		this.productName = productName;
		this.productSubName = productSubName;
		this.userCode = userCode;
		this.productMasterCode = productMasterCode;
		ProductMasterSubCode = productMasterSubCode;
		this.mandiName = mandiName;
		this.quantity = quantity;
		this.expectedPrice = expectedPrice;
		this.totalPrice = totalPrice;
		this.location = location;
		this.quality = quality;
		this.distanceFromMandi = distanceFromMandi;
		this.orderOtp = orderOtp;
		this.orderCode = orderCode;
		this.harvestData = harvestData;
		this.transactionId = transactionId;
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "AvailableProductForSellResponse [productName=" + productName + ", productSubName=" + productSubName
				+ ", userCode=" + userCode + ", productMasterCode=" + productMasterCode + ", ProductMasterSubCode="
				+ ProductMasterSubCode + ", mandiName=" + mandiName + ", quantity=" + quantity + ", expectedPrice="
				+ expectedPrice + ", totalPrice=" + totalPrice + ", location=" + location + ", quality=" + quality
				+ ", distanceFromMandi=" + distanceFromMandi + ", orderOtp=" + orderOtp + ", orderCode=" + orderCode
				+ ", harvestData=" + harvestData + ", transactionId=" + transactionId + ", imageData=" + imageData
				+ "]";
	}

	public AvailableProductForSellResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
