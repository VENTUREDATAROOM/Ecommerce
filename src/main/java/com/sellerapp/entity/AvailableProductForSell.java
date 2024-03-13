package com.sellerapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AvailableProductForSell {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String productName;
	private String productSubName;
	private String userCode;
	private String productMasterCode;
	private String ProductMasterSubCode;
	private String mandiName;
	private String quantity;
	private String address;
	private String expectedPrice;
	private String totalPrice;
	private String location;
	private String quality;
	private String distanceFromMandi;
	private String orderOtp;
	private String orderCode;
	private String harvestDate;
	private String transactionId;

}
