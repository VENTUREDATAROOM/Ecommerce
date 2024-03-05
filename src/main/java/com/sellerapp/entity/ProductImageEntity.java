package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "product_image_details")
public class ProductImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_sequence")
	@SequenceGenerator(name = "product_image_sequence", sequenceName = "product_image_seq")
	@Column(name = "image_id")
	private Long imageId;

	@Lob
	@Column(name = "product_image")
	private byte[] imageData;

	@OneToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

}
