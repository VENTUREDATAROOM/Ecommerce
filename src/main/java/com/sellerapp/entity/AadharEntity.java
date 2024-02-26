package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="aadhar_details")
public class AadharEntity {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="aadhar_id")
	private Long Id;
	@Column(name="aadhar_no")
	private String aadharNumber;
	
	@Column(name="frontpage_pic")
	private String frontPage;
	
	@Column(name="backpage_pic")
	private String backPage;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getFrontPage() {
		return frontPage;
	}
	public void setFrontPage(String frontPage) {
		this.frontPage = frontPage;
	}
	public String getBackPage() {
		return backPage;
	}
	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}
	
	public AadharEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AadharEntity(Long id, String aadharNumber, String frontPage, String backPage) {
		super();
		Id = id;
		this.aadharNumber = aadharNumber;
		this.frontPage = frontPage;
		this.backPage = backPage;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
