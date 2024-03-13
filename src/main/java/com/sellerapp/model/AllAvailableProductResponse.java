package com.sellerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllAvailableProductResponse {

	private Long id;
	private String productName;
	private String productSubName;
	private String userCode;
	private String productMasterCode;
	private String ProductMasterSubCode;
	private String mandiName;
	private String quantity;
	private String expectedPrice;
	private String totalPrice;
	private String location;
	private String address;
	private String quality;
	private String distanceFromMandi;
	private String orderOtp;
	private String orderCode;
	private String harvestDate;
	private String transactionId;
	private String imgeOfProduct;

}
