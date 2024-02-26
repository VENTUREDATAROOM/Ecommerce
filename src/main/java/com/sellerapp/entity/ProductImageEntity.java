package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="product_image_details")
public class ProductImageEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_seq")
	@Column(name="prodcut_id")
	private Long imageId;
	
	@Column(name="product_image")
	private byte[]  imageData;
  
   @OneToOne
   @JoinColumn(name="product_id")
   private ProductEntity product;
	

	

	

   

	public ProductEntity getProduct() {
	return product;
}

public void setProduct(ProductEntity product) {
	this.product = product;
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
