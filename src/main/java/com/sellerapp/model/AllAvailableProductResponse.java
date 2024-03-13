package com.sellerapp.model;




public class AllAvailableProductResponse {

	private Long id;
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
	private String address;
	private String quality;
	private String distanceFromMandi;
	private String orderOtp;
	private String orderCode;
	private String harvestDate;
	private String transactionId;
	private String imgeOfProduct;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getImgeOfProduct() {
		return imgeOfProduct;
	}
	public void setImgeOfProduct(String imgeOfProduct) {
		this.imgeOfProduct = imgeOfProduct;
	}
	@Override
	public String toString() {
		return "AllAvailableProductResponse [id=" + id + ", productName=" + productName + ", productSubName="
				+ productSubName + ", userCode=" + userCode + ", productMasterCode=" + productMasterCode
				+ ", ProductMasterSubCode=" + ProductMasterSubCode + ", mandiName=" + mandiName + ", quantity="
				+ quantity + ", expectedPrice=" + expectedPrice + ", totalPrice=" + totalPrice + ", location="
				+ location + ", address=" + address + ", quality=" + quality + ", distanceFromMandi="
				+ distanceFromMandi + ", orderOtp=" + orderOtp + ", orderCode=" + orderCode + ", harvestDate="
				+ harvestDate + ", transactionId=" + transactionId + ", imgeOfProduct=" + imgeOfProduct + "]";
	}
	public AllAvailableProductResponse(Long id, String productName, String productSubName, String userCode,
			String productMasterCode, String productMasterSubCode, String mandiName, String quantity,
			String expectedPrice, String totalPrice, String location, String address, String quality,
			String distanceFromMandi, String orderOtp, String orderCode, String harvestDate, String transactionId,
			String imgeOfProduct) {
		super();
		this.id = id;
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
		this.address = address;
		this.quality = quality;
		this.distanceFromMandi = distanceFromMandi;
		this.orderOtp = orderOtp;
		this.orderCode = orderCode;
		this.harvestDate = harvestDate;
		this.transactionId = transactionId;
		this.imgeOfProduct = imgeOfProduct;
	}
	public AllAvailableProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
