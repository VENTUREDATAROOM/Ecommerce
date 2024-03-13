package com.sellerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvailableProductRequest {

	private String productName;
	private String mandiName;
	private String quantity;
	private String quality;
	private String price;
	private String address;
	private String distanceFromMandi;
	private String harvestDate;

}
