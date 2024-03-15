package com.sellerapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.AvailableProductForSell;
import com.sellerapp.entity.AvailableProductImage;
import com.sellerapp.entity.ProductForSell;
import com.sellerapp.entity.ProductForSellPhoto;
import com.sellerapp.model.AllAvailableProductResponse;
import com.sellerapp.model.AvailableProductForSellResponse;
import com.sellerapp.model.AvailableProductRequest;
import com.sellerapp.model.AvailableProductResponse;
import com.sellerapp.repository.AvailableProductForSellRepository;
import com.sellerapp.repository.AvailableProductImageRepository;
import com.sellerapp.repository.ProductForSellPhotoRepository;
import com.sellerapp.repository.ProductForSellRepository;

@Service
public class AvailableProductForSellService {

	@Autowired
	private AvailableProductForSellRepository AvailableProductRepo;

	@Autowired
	private AvailableProductImageRepository availableProductImagerepo;

	@Autowired
	private ProductForSellRepository productForSellRepo;

	@Autowired
	private ProductForSellPhotoRepository ProductForSellPhotoRepo;

	public AvailableProductResponse addAvailableProductFoSell(AvailableProductRequest ProductData,
			List<MultipartFile> ImageFileData, String ProductMasterSubcode, String UserCode) {

		AvailableProductForSell FormDataEntity = new AvailableProductForSell();
		AvailableProductImage ImageDataEntity = new AvailableProductImage();
		AvailableProductResponse ResponseToUser = new AvailableProductResponse();

		ProductForSell ProductForSellData = this.productForSellRepo.findByProductMasterSubCode(ProductMasterSubcode);

		try {
			FormDataEntity.setProductName(ProductData.getProductName());
			FormDataEntity.setProductSubName(ProductForSellData.getProductSubName());
			FormDataEntity.setProductMasterCode(ProductForSellData.getProductMasterCode());
			FormDataEntity.setProductMasterSubCode(ProductForSellData.getProductMasterSubCode());
			FormDataEntity.setQuality(ProductData.getQuality());
			FormDataEntity.setMandiName(ProductData.getMandiName());
			FormDataEntity.setExpectedPrice(ProductData.getPrice());
			FormDataEntity.setQuantity(ProductData.getQuantity());

			// int ExpectedPrice = Integer.parseInt("100");
			int totalPrice = Integer.parseInt(ProductData.getPrice()) * Integer.parseInt(ProductData.getQuantity());
			FormDataEntity.setTotalPrice(Integer.toString(totalPrice));
			FormDataEntity.setLocation("15.776395,17.830100");

			FormDataEntity.setAddress(ProductData.getAddress());

			int OderOtp = (int) (Math.random() * 10000);
			FormDataEntity.setOrderOtp(Integer.toString(OderOtp));

			String OrderCode = codeGenerator();

			FormDataEntity.setOrderCode(OrderCode);
			FormDataEntity.setHarvestDate(ProductData.getHarvestDate());
			LocalDateTime Transactiondate = LocalDateTime.now();
			String newStr = Transactiondate.toString();
			String transactionId = newStr.substring(0, 4) + newStr.substring(5, 7) + newStr.substring(8, 13)
					+ newStr.substring(14, 16) + newStr.substring(17, 19) + newStr.substring(20);
			FormDataEntity.setTransactionId(transactionId);
			FormDataEntity.setDistanceFromMandi(ProductData.getDistanceFromMandi());
           
			FormDataEntity.setStatus("Created");
			
			FormDataEntity.setUserCode(UserCode);
			AvailableProductForSell ResponseData = this.AvailableProductRepo.save(FormDataEntity);

			if (ResponseData == null) {
				return null;
			}

			byte[] Image1 = null;
			byte[] Image2 = null;
			byte[] Image3 = null;

			if (ImageFileData.size() == 1) {
				Image1 = ImageFileData.get(0).getBytes();
			} else if (ImageFileData.size() == 2) {
				Image1 = ImageFileData.get(0).getBytes();
				Image2 = ImageFileData.get(1).getBytes();
			} else {
				Image1 = ImageFileData.get(0).getBytes();
				Image2 = ImageFileData.get(1).getBytes();
				Image3 = ImageFileData.get(2).getBytes();
			}

			ImageDataEntity.setImage1(Image1);
			ImageDataEntity.setImage2(Image2);
			ImageDataEntity.setImage3(Image3);

			ImageDataEntity.setOrderCode(OrderCode);

			AvailableProductImage ResponseImageData = this.availableProductImagerepo.save(ImageDataEntity);

			if (ResponseImageData == null) {
				return null;
			}
			ResponseToUser.setStatus("Add successfully your Product Ready For Sell !!");
			return ResponseToUser;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public List<AllAvailableProductResponse> getAllDataForSellProduct() {
		try {
			List<AvailableProductForSell> ProductData = this.AvailableProductRepo.findAll();

			List<String> forFilterData = new ArrayList<>();

			List<AllAvailableProductResponse> ResponseListData = new ArrayList<>();

			for (AvailableProductForSell item : ProductData) {
				// if (!forFilterData.contains(item.getProductMasterCode())) {
				// forFilterData.add(item.getProductMasterCode());

				AllAvailableProductResponse ResponseData = new AllAvailableProductResponse();
				ResponseData.setId(item.getId());
				ResponseData.setProductName(item.getProductName());
				ResponseData.setProductSubName(item.getProductSubName());
				ResponseData.setUserCode(item.getUserCode());
				ResponseData.setProductMasterCode(item.getProductMasterCode());
				ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
				ResponseData.setMandiName(item.getMandiName());
				ResponseData.setQuantity(item.getQuantity());
				ResponseData.setQuality(item.getQuality());
				ResponseData.setExpectedPrice(item.getExpectedPrice());
				ResponseData.setTotalPrice(item.getTotalPrice());
				ResponseData.setLocation(item.getLocation());
				ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
				ResponseData.setOrderCode(item.getOrderCode());
				ResponseData.setOrderOtp(item.getOrderOtp());
				ResponseData.setAddress(item.getAddress());
				ResponseData.setHarvestDate(item.getHarvestDate());
				ResponseData.setTransactionId(item.getTransactionId());
				ResponseData.setImgeOfProduct(productImageToString(item.getProductMasterSubCode()));

				ResponseListData.add(ResponseData);
				// }
			}
			return ResponseListData;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public String productImageToString(String ProductMasterSubCode) {
		ProductForSellPhoto data = this.ProductForSellPhotoRepo.findByProductMasterSubCode(ProductMasterSubCode);
		String imageData = byteImageToBase64(data.getProductImage());
		return imageData;
	}

	public List<AvailableProductForSellResponse> getallAvailableProductData(String productMasterCode) {

		List<AvailableProductForSell> ProductData = this.AvailableProductRepo
				.findByProductMasterCode(productMasterCode);
		List<AvailableProductForSellResponse> ResponseListData = new ArrayList<>();

		try {

			for (AvailableProductForSell item : ProductData) {
				AvailableProductForSellResponse ResponseData = new AvailableProductForSellResponse();
				ResponseData.setProductName(item.getProductName());
				ResponseData.setProductSubName(item.getProductSubName());
				ResponseData.setUserCode(item.getUserCode());
				ResponseData.setProductMasterCode(item.getProductMasterCode());
				ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
				ResponseData.setMandiName(item.getMandiName());
				ResponseData.setQuality(item.getQuality());
				ResponseData.setExpectedPrice(item.getExpectedPrice());
				ResponseData.setTotalPrice(item.getTotalPrice());
				ResponseData.setLocation(item.getLocation());
				ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
				ResponseData.setOrderCode(item.getOrderCode());
				ResponseData.setOrderOtp(item.getOrderOtp());
				ResponseData.setAddress(item.getAddress());
				ResponseData.setHarvestDate(item.getHarvestDate());
				ResponseData.setTransactionId(item.getTransactionId());
				ResponseData.setQuantity(item.getQuantity());
				List<String> ImageData = GetImageBase64(item.getOrderCode());
				ResponseData.setImageData(ImageData);

				ResponseListData.add(ResponseData);
			}
			return ResponseListData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public AvailableProductForSellResponse getSingleAvailableProductData(String OrderCode) {

		AvailableProductForSell item = this.AvailableProductRepo.findByOrderCode(OrderCode);
		List<AvailableProductForSellResponse> ResponseListData = new ArrayList<>();

		try {

			// for (AvailableProductForSell item : ProductData) {
			AvailableProductForSellResponse ResponseData = new AvailableProductForSellResponse();
			ResponseData.setProductName(item.getProductName());
			ResponseData.setProductSubName(item.getProductSubName());
			ResponseData.setUserCode(item.getUserCode());
			ResponseData.setProductMasterCode(item.getProductMasterCode());
			ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
			ResponseData.setMandiName(item.getMandiName());
			ResponseData.setQuality(item.getQuality());
			ResponseData.setExpectedPrice(item.getExpectedPrice());
			ResponseData.setTotalPrice(item.getTotalPrice());
			ResponseData.setLocation(item.getLocation());
			ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
			ResponseData.setAddress(item.getAddress());
			ResponseData.setOrderCode(item.getOrderCode());
			ResponseData.setOrderOtp(item.getOrderOtp());
			ResponseData.setHarvestDate(item.getHarvestDate());
			ResponseData.setTransactionId(item.getTransactionId());
			ResponseData.setQuantity(item.getQuantity());
			List<String> ImageData = GetImageBase64(item.getOrderCode());
			ResponseData.setImageData(ImageData);

			// ResponseListData.add(ResponseData);
			// }
			return ResponseData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public List<AvailableProductForSellResponse> getallAvailableProductDataByuserCode(String UserCode) {

		List<AvailableProductForSell> ProductData = this.AvailableProductRepo
				.findByUserCode(UserCode);
		List<AvailableProductForSellResponse> ResponseListData = new ArrayList<>();

		try {

			for (AvailableProductForSell item : ProductData) {
				AvailableProductForSellResponse ResponseData = new AvailableProductForSellResponse();
				ResponseData.setProductName(item.getProductName());
				ResponseData.setProductSubName(item.getProductSubName());
				ResponseData.setUserCode(item.getUserCode());
				ResponseData.setProductMasterCode(item.getProductMasterCode());
				ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
				ResponseData.setMandiName(item.getMandiName());
				ResponseData.setQuality(item.getQuality());
				ResponseData.setExpectedPrice(item.getExpectedPrice());
				ResponseData.setTotalPrice(item.getTotalPrice());
				ResponseData.setLocation(item.getLocation());
				ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
				ResponseData.setOrderCode(item.getOrderCode());
				ResponseData.setOrderOtp(item.getOrderOtp());
				ResponseData.setAddress(item.getAddress());
				ResponseData.setHarvestDate(item.getHarvestDate());
				ResponseData.setTransactionId(item.getTransactionId());
				ResponseData.setQuantity(item.getQuantity());
				List<String> ImageData = GetImageBase64(item.getOrderCode());
				ResponseData.setImageData(ImageData);

				ResponseListData.add(ResponseData);
			}
			return ResponseListData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<AvailableProductForSellResponse> getallAvailableProductDataWhichSold(String UserCode) {

		List<AvailableProductForSell> ProductData = this.AvailableProductRepo
				.findByUserCode(UserCode);
		List<AvailableProductForSellResponse> ResponseListData = new ArrayList<>();

		try {

			for (AvailableProductForSell item : ProductData) {
				
				if(item.getStatus().equals("completed")) {
					
				AvailableProductForSellResponse ResponseData = new AvailableProductForSellResponse();
				ResponseData.setProductName(item.getProductName());
				ResponseData.setProductSubName(item.getProductSubName());
				ResponseData.setUserCode(item.getUserCode());
				ResponseData.setProductMasterCode(item.getProductMasterCode());
				ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
				ResponseData.setMandiName(item.getMandiName());
				ResponseData.setQuality(item.getQuality());
				ResponseData.setExpectedPrice(item.getExpectedPrice());
				ResponseData.setTotalPrice(item.getTotalPrice());
				ResponseData.setLocation(item.getLocation());
				ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
				ResponseData.setOrderCode(item.getOrderCode());
				ResponseData.setOrderOtp(item.getOrderOtp());
				ResponseData.setAddress(item.getAddress());
				ResponseData.setHarvestDate(item.getHarvestDate());
				ResponseData.setTransactionId(item.getTransactionId());
				ResponseData.setQuantity(item.getQuantity());
				List<String> ImageData = GetImageBase64(item.getOrderCode());
				ResponseData.setImageData(ImageData);

				ResponseListData.add(ResponseData);
				}
			}
			return ResponseListData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	

	public List<String> GetImageBase64(String availableCode) {
		AvailableProductImage ProductImage = this.availableProductImagerepo.findByOrderCode(availableCode);

		if (ProductImage == null) {
			return null;
		}

		List<String> ImageData = new ArrayList<>();
		if (ProductImage.getImage1() != null) {
			ImageData.add(byteImageToBase64(ProductImage.getImage1()));
		}
		if (ProductImage.getImage2() != null) {
			ImageData.add(byteImageToBase64(ProductImage.getImage2()));
		}
		if (ProductImage.getImage3() != null) {
			ImageData.add(byteImageToBase64(ProductImage.getImage3()));
		}

		return ImageData;
	}

//	public List<AllAvailableProductResponse> getAllEspecificProductdataForsell(String ProductCode) {
//
//		List<AllAvailableProductResponse> AllResponseData = new ArrayList<>();
//		try {
//			List<AvailableProductForSell> ProductData = this.AvailableProductRepo.findByProductCode(ProductCode);
////			AvailableProductImage ProductImage = this.availableProductImagerepo
////					.findByProductCode(ProductCode);
//
//			for (AvailableProductForSell item : ProductData) {
//				AllAvailableProductResponse ResponseData = new AllAvailableProductResponse();
//				ResponseData.setId(item.getId());
//				ResponseData.setProductName(item.getProductName());
//				ResponseData.setMandiName(item.getMandiName());
//				ResponseData.setQuality(item.getQuality());
//				ResponseData.setQuantity(item.getQuantity());
//				ResponseData.setPrice(item.getPrice());
//				ResponseData.setDistanceFromMandi(item.getDistanceFromMandi());
//
//				AllResponseData.add(ResponseData);
//			}
//			return AllResponseData;
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
//	}

	public static byte[] base64ToByteImage(String base64String) {
		byte[] imageBytes = Base64.getDecoder().decode(base64String);
		return imageBytes;
	}

	public static String byteImageToBase64(byte[] imageBytes) {
		// Encode the byte array to Base64
		String base64String = Base64.getEncoder().encodeToString(imageBytes);
		return base64String;
	}

	static String codeGenerator() {
		String alphabetsInUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetsInLowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		// create a super set of all characters
		String allCharacters = alphabetsInLowerCase + alphabetsInUpperCase + numbers;
		// initialize a string to hold result
		StringBuffer randomString = new StringBuffer();
		// loop for 10 times
		for (int i = 0; i < 10; i++) {
			// generate a random number between 0 and length of all characters
			int randomIndex = (int) (Math.random() * allCharacters.length());
			// retrieve character at index and add it to result
			randomString.append(allCharacters.charAt(randomIndex));
		}
		return randomString.toString();
	}

}
