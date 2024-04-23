package com.sellerapp.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class AvailableProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	//@Column(name = "order_code")
	private String orderCode;
	//@Column(name = "image_1")
	private byte[] image1;
	//@Column(name = "image_2")
	private byte[] image2;
//	@Column(name = "image_3")
	private byte[] image3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public AvailableProductImage(Long id, String orderCode, byte[] image1, byte[] image2, byte[] image3) {
		super();
		this.id = id;
		this.orderCode = orderCode;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}

	public AvailableProductImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AvailableProductImage [id=" + id + ", orderCode=" + orderCode + ", image1=" + Arrays.toString(image1)
				+ ", image2=" + Arrays.toString(image2) + ", image3=" + Arrays.toString(image3) + "]";
	}

}
