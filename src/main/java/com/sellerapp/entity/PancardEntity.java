package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;



@Entity
@Table(name="pancard_details")
public class PancardEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pancard_id")
	private Long id;
	@Column(name="pancard_number")
	private String pancardNumber;
	@Column(name="pancard_pic")
	private String[] pic;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}
	public String[] getPic() {
		return pic;
	}
	public void setPic(String[] pic) {
		this.pic = pic;
	}
	
	

     
}
