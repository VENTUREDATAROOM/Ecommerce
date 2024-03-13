package com.sellerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellerapp.model.AllAvailableProductResponse;
import com.sellerapp.model.AvailableProductForSellResponse;
import com.sellerapp.model.AvailableProductRequest;
import com.sellerapp.model.AvailableProductResponse;
import com.sellerapp.service.AvailableProductForSellService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/available")
@Tag(name = "Order Api")
public class AvailableProductForSellController {

	@Autowired
	private AvailableProductForSellService AvailableProductForSellservice;

	@PostMapping("/add/productForSell/{UserCode}")
	@Operation(summary = "API for Placed Order")
	public ResponseEntity<?> addAvailableProductForSell(@RequestParam("ListImage") List<MultipartFile> ImageData,
			@RequestParam("data") String FormData, @RequestParam("ProductMasterSubCode") String ProductMasterSubCode,
			@PathVariable("UserCode") String UserCode) {

		ObjectMapper Object = new ObjectMapper();
		AvailableProductResponse ResponseToUser = new AvailableProductResponse();
		AvailableProductRequest ProductData = null;
		try {
			ProductData = Object.readValue(FormData, AvailableProductRequest.class);
		} catch (Exception e) {
			return new ResponseEntity<String>("opps ! object mapping wrong !!", HttpStatus.BAD_REQUEST);
		}

		try {

			AvailableProductResponse ResponseData = this.AvailableProductForSellservice
					.addAvailableProductFoSell(ProductData, ImageData, ProductMasterSubCode, UserCode);
			if (ResponseData == null) {
				throw new NullPointerException("error_NoData");
			}
			return new ResponseEntity<AvailableProductResponse>(ResponseData, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseToUser.setStatus("some thing wrong !!");
			return new ResponseEntity<AvailableProductResponse>(ResponseToUser, HttpStatus.BAD_GATEWAY);
		}

	}

	@GetMapping("/get/allproductForSell")
	@Operation(summary = "API for get All List of Placed  Order")
	public ResponseEntity<?> getAllproductData() {
		try {
			List<AllAvailableProductResponse> ProductData = this.AvailableProductForSellservice
					.getAllDataForSellProduct();
			return new ResponseEntity<List<AllAvailableProductResponse>>(ProductData, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			AvailableProductResponse ResponseToUser = new AvailableProductResponse();
			ResponseToUser.setStatus("some thing wrong !!");
			return new ResponseEntity<AvailableProductResponse>(ResponseToUser, HttpStatus.BAD_GATEWAY);

		}
	}

//	@PostMapping("/get/allEspecificProductForSell")
//	public ResponseEntity<?> getAllProductforSellRelatedOne(@RequestParam("ProductMaterCode") String ProductMaterCode) {
//		try {
//			List<AvailableProductForSellResponse> ResponseData = this.AvailableProductForSellservice
//					.getallAvailableProductData(ProductMaterCode);
//			return new ResponseEntity<List<AvailableProductForSellResponse>>(ResponseData, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<String>("Bad GetWay", HttpStatus.BAD_GATEWAY);
//		}
//	}

	@PostMapping("/get/SingleEspecificProductForSell")
	@Operation(summary = "API for get one single placed Order Data")
	public ResponseEntity<?> getSingleProductforSellRelatedOne(@RequestParam("OrderCode") String OrderCode) {
		try {
			AvailableProductForSellResponse ResponseData = this.AvailableProductForSellservice
					.getSingleAvailableProductData(OrderCode);
			return new ResponseEntity<AvailableProductForSellResponse>(ResponseData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bad GetWay", HttpStatus.BAD_GATEWAY);
		}
	}

//	@GetMapping("/get/SingleEspecificData/{availableCode}")
//	public ResponseEntity<?> getSingleEspecificProductdata(@PathVariable("availableCode") String codeData) {
//		try {
//
//			List<AllAvailableProductResponse> ResponseData = this.AvailableProductForSellservice
//					.getAllEspecificProductdataForsell(codeData);
//			return new ResponseEntity<List<AllAvailableProductResponse>>(ResponseData, HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return new ResponseEntity<String>("something wrong  ..!", HttpStatus.BAD_GATEWAY);
//		}
//
//	}

}
