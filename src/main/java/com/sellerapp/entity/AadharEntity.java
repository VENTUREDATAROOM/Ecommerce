package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aadhar_details")
public class AadharEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aadhar_id")
	private Long id;
	@Column(name = "aadhar_no" , unique = true)
	private String aadharNumber;

	@Column(name = "frontpage_pic")
	private byte[] frontPage;

	@Column(name = "backpage_pic")
	private byte[] backPage;
	
	private String userCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public byte[] getFrontPage() {
		return frontPage;
	}

	public void setFrontPage(byte[] frontPage) {
		this.frontPage = frontPage;
	}

	public byte[] getBackPage() {
		return backPage;
	}

	public void setBackPage(byte[] backPage) {
		this.backPage = backPage;
	}

	public AadharEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public AadharEntity(Long id, String aadharNumber, byte[] frontPage, byte[] backPage, String userCode) {
		super();
		this.id = id;
		this.aadharNumber = aadharNumber;
		this.frontPage = frontPage;
		this.backPage = backPage;
		this.userCode = userCode;
	}

	

}