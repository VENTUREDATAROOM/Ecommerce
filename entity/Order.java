package com.sellerapp.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity

@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	@SequenceGenerator(name = "order_sequence", sequenceName = "order_seq")
	@Column(name = "order_id")
	private Long id;

	@Column(name = "product_id")
	private String productId;

	@Column(name = "harvest_date")
	private LocalDate harvestDate;

	@Column(name = "Address")
	private String address;

	@Column(name = "mandi_name")
	private String mandiName;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "quality")
	private String quality;

	@Column(name = "price")
	private double price;

	@Column(name = "distance_From_Mandi")
	private String distanceFromMandi;
	@ElementCollection
	@Column(name = "images")
	private List<byte[]> productImages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public LocalDate getHarvestDate() {
		return harvestDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDistanceFromMandi() {
		return distanceFromMandi;
	}

	public void setDistanceFromMandi(String distanceFromMandi) {
		this.distanceFromMandi = distanceFromMandi;
	}

	public List<byte[]> getProductImages() {
		return productImages;
	}

	public void setHarvestDate(LocalDate harvestDate) {
		this.harvestDate = harvestDate;
	}

	public void setProductImages(List<byte[]> productImages) {
		this.productImages = productImages;
	}

}
