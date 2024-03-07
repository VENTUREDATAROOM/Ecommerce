package com.sellerapp.model;

public class AvailableProductRequest {

	private String productName;
	private String mandiName;
	private String quantity;
	private String quality;
	private String price;
	private String distanceFromMandi;
	private String harvestData;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDistanceFromMandi() {
		return distanceFromMandi;
	}

	public void setDistanceFromMandi(String distanceFromMandi) {
		this.distanceFromMandi = distanceFromMandi;
	}

	public String getHarvestData() {
		return harvestData;
	}

	public void setHarvestData(String harvestData) {
		this.harvestData = harvestData;
	}

	public AvailableProductRequest(String productName, String mandiName, String quantity, String quality, String price,
			String distanceFromMandi, String harvestData) {
		super();
		this.productName = productName;
		this.mandiName = mandiName;
		this.quantity = quantity;
		this.quality = quality;
		this.price = price;
		this.distanceFromMandi = distanceFromMandi;
		this.harvestData = harvestData;
	}

	public AvailableProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AvailableProductRequest [productName=" + productName + ", mandiName=" + mandiName + ", quantity="
				+ quantity + ", quality=" + quality + ", price=" + price + ", distanceFromMandi=" + distanceFromMandi
				+ ", harvestData=" + harvestData + "]";
	}

}
