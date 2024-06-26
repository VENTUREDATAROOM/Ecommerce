package com.sellerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.ProductForSell;
import com.sellerapp.model.ProductForSellResponse;
import com.sellerapp.service.ProductForSellService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Product-API")
public class ProductForSellController {

	@Autowired
	private ProductForSellService ProductForSellService;

	@PostMapping(value="add/productForSell",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "API for add Product")
	public String addDataOfProductForSell(@RequestParam("ProductName") String ProductName,
			@RequestParam("ProductSubName") String ProductSubName,
			@RequestParam("ProductImage") MultipartFile ImageFile) {

		try {

			String data = this.ProductForSellService.addProductForSell(ProductName, ProductSubName, ImageFile);
			if (data == null) {
				throw new NullPointerException("No Data Inserted !!");
			}
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return "opps! data not Inserted !!";
		}
	}

	@GetMapping("/get/allProductData")
	@Operation(summary = "API for get List Of All Product")
	public ResponseEntity<?> getAllProductForSellData() {
		try {

			List<ProductForSellResponse> ResponseData = this.ProductForSellService.getAllPoductData();
			if (ResponseData == null) {
				throw new NullPointerException("No Such Data Found !");
			}
			return new ResponseEntity<List<ProductForSellResponse>>(ResponseData, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bad Request !!", HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/get/singleDataOfProduct")
	@Operation(summary = "API for get one single data(required ProductFinalCode in form data)")
	public ResponseEntity<?> getSingleDataOfProduct(@RequestParam("ProductFinalCode") String ProductFinalCode) {
		try {
			ProductForSellResponse ResponseData = this.ProductForSellService.getSingleData(ProductFinalCode);
			if (ResponseData == null) {
				throw new NullPointerException("No Such Data Found !");
			}
			return new ResponseEntity<ProductForSellResponse>(ResponseData, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bad GetWay", HttpStatus.BAD_GATEWAY);
		}
	}

	@PostMapping("/get/allSubProduct")
	@Operation(summary = "API for get all sub product like new and old potato")
	public ResponseEntity<?> GetProductSubName(@RequestParam("ProductMasterCode") String ProductMasterCode) {
		try {
			List<ProductForSell> ResponseData = this.ProductForSellService.getListOfProduct(ProductMasterCode);
			if (ResponseData == null) {
				throw new NullPointerException("No Such Data Found !");
			}
			return new ResponseEntity<List<ProductForSell>>(ResponseData, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Bad GetWay", HttpStatus.BAD_GATEWAY);
		}

	}
	
	

}
