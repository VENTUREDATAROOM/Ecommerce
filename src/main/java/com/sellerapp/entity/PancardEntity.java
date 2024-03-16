package com.sellerapp.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pancard_details")
public class PancardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pancard_id")
	private Long id;
	@Column(name = "pancard_number")
	private String pancardNumber;
	@Column(name = "pancard_pic")
	private byte[] pic;
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public PancardEntity(Long id, String pancardNumber, byte[] pic, String userCode) {
		super();
		this.id = id;
		this.pancardNumber = pancardNumber;
		this.pic = pic;
		this.userCode = userCode;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public PancardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PancardEntity [id=" + id + ", pancardNumber=" + pancardNumber + ", pic=" + Arrays.toString(pic)
				+ ", userCode=" + userCode + "]";
	}
	
	
}