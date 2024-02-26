package com.sellerapp.model;

import java.util.List;

public class SellVegetableDTO {
	
	private Long id;
	private String name;
	private List<String> images;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	

}
