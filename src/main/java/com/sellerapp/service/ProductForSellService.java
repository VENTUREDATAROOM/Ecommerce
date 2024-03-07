package com.sellerapp.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.ProductForSell;
import com.sellerapp.entity.ProductForSellPhoto;
import com.sellerapp.model.ProductForSellResponse;
import com.sellerapp.repository.ProductForSellPhotoRepository;
import com.sellerapp.repository.ProductForSellRepository;

@Service
public class ProductForSellService {

	@Autowired
	private ProductForSellRepository ProductForSellRepo;

	@Autowired
	private ProductForSellPhotoRepository ProductForSellPhotoRepo;

	public List<ProductForSell> findProductByName(String productName) {
		List<ProductForSell> data = this.ProductForSellRepo.findByProductName(productName);
		if (data == null) {
			return null;
		} else {
			return data;
		}
	}

	public String addProductForSell(String productName, String productSubName, MultipartFile Image) {

		ProductForSell ProductEntity = new ProductForSell();
		ProductForSellPhoto ProductPhotoEntity = new ProductForSellPhoto();
		List<ProductForSell> ProductData = findProductByName(productName);
		String ProductMasterCode = null;
		if (ProductData.size() == 0) {
			ProductMasterCode = codeGenerator();
		} else {
			ProductMasterCode = ProductData.get(0).getProductMasterCode();
		}

		String ProductMasterSubCode = codeGenerator();
		String ProductFinalCode = ProductMasterCode + ProductMasterSubCode;

		try {

			ProductEntity.setProductName(productName);
			ProductEntity.setProductSubName(productSubName);
			ProductEntity.setProductMasterCode(ProductMasterCode);
			ProductEntity.setProductMasterSubCode(ProductMasterSubCode);
			ProductEntity.setProductFinalCode(ProductFinalCode);
			ProductForSell ProductForSellData = this.ProductForSellRepo.save(ProductEntity);
			if (ProductForSellData == null) {
				return null;
			}

			// ProductForSellPhoto.

			ProductPhotoEntity.setProductMasterSubCode(ProductMasterSubCode);
			ProductPhotoEntity.setProductImage(Image.getBytes());
			ProductPhotoEntity.setProductMasterCode(ProductMasterCode);
			ProductForSellPhoto productForSellPhotoData = this.ProductForSellPhotoRepo.save(ProductPhotoEntity);
			if (productForSellPhotoData == null) {
				return null;
			}

			return "Successfully Add productData..!";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getImageBase64(String ProductSubCode) {
		ProductForSellPhoto data = this.ProductForSellPhotoRepo.findByProductMasterSubCode(ProductSubCode);
		String ImageData = byteImageToBase64(data.getProductImage());
		return ImageData;
	}

	public List<ProductForSellResponse> getAllPoductData() {
		List<ProductForSell> ProductForSellData = this.ProductForSellRepo.findAll();
		List<ProductForSellResponse> ResponseListData = new ArrayList<>();

		List<String> ProductMasCode = new ArrayList<>();
		try {
			for (ProductForSell item : ProductForSellData) {
				if (!ProductMasCode.contains(item.getProductMasterCode())) {
					ProductMasCode.add(item.getProductMasterCode());

					ProductForSellResponse ResponseData = new ProductForSellResponse();
					ResponseData.setId(item.getId());
					ResponseData.setProductName(item.getProductName());
					ResponseData.setProductSubName(item.getProductSubName());
					ResponseData.setProductMasterCode(item.getProductMasterCode());
					ResponseData.setProductMasterSubCode(item.getProductMasterSubCode());
					ResponseData.setProductFinalCode(item.getProductFinalCode());
					String ImageData = getImageBase64(item.getProductMasterSubCode());
					ResponseData.setProductImage(ImageData);

					ResponseListData.add(ResponseData);
				}
			}
			return ResponseListData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public ProductForSellResponse getSingleData(String ProductFinalCode) {

		try {

			ProductForSellResponse productForSellResponseData = new ProductForSellResponse();
			ProductForSell ResponseData = this.ProductForSellRepo.findByProductFinalCode(ProductFinalCode);
			productForSellResponseData.setProductName(ResponseData.getProductName());
			productForSellResponseData.setProductSubName(ResponseData.getProductSubName());
			productForSellResponseData.setProductMasterCode(ResponseData.getProductMasterCode());
			productForSellResponseData.setProductSubName(ResponseData.getProductSubName());
			productForSellResponseData.setProductFinalCode(ResponseData.getProductFinalCode());
			productForSellResponseData.setProductMasterSubCode(ResponseData.getProductMasterSubCode());
			String ImageData = getImageBase64(ResponseData.getProductMasterSubCode());
			productForSellResponseData.setProductImage(ImageData);
			return productForSellResponseData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<ProductForSell> getListOfProduct(String ProductMasterCode) {
		try {
			List<ProductForSell> ProductData = this.ProductForSellRepo.findByProductMasterCode(ProductMasterCode);
			return ProductData;

		} catch (Exception e) {
			return null;
		}

	}

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
