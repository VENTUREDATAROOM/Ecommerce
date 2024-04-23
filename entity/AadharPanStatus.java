package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aadhar_pan_status")
public class AadharPanStatus {
	@Id
	@Column(name="userCode")
	private String userCode;
	@Column(name="pan_status")
	private String panStatus;
	@Column(name="aadhar_status")
	private String aadharStatus;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPanStatus() {
		return panStatus;
	}
	public void setPanStatus(String panStatus) {
		this.panStatus = panStatus;
	}
	public String getAadharStatus() {
		return aadharStatus;
	}
	public void setAadharStatus(String aadharStatus) {
		this.aadharStatus = aadharStatus;
	}
	
	
	
	

}
