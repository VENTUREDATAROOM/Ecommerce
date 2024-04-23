package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aadhar_pan_photo")
public class AadharPanPhoto {

	@Id
	@Column(name="userCode")
	private String userCode;
	@Column(name="pan_photo")
	private byte[] panPhoto;
	@Column(name="aadhar_back")
	private byte[] aadharBack;
	@Column(name="aadhar_front")
	private byte[] aadharFront;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public byte[] getPanPhoto() {
		return panPhoto;
	}
	public void setPanPhoto(byte[] panPhoto) {
		this.panPhoto = panPhoto;
	}
	public byte[] getAadharBack() {
		return aadharBack;
	}
	public void setAadharBack(byte[] aadharBack) {
		this.aadharBack = aadharBack;
	}
	public byte[] getAadharFront() {
		return aadharFront;
	}
	public void setAadharFront(byte[] aadharFront) {
		this.aadharFront = aadharFront;
	}
	
	
}
