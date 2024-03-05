package com.sellerapp.service;

import java.util.Base64;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.ProductEntity;
import com.sellerapp.entity.ProductImageEntity;
import com.sellerapp.model.ProductDTO;
import com.sellerapp.model.ProductDTO2;
import com.sellerapp.repository.ProductImageRepo;
import com.sellerapp.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	ModelMapper mapper;

	@Autowired
	private ProductImageRepo productImageRepo;
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductService.class);

	public List<ProductEntity> getAllProduct() {
		return productRepo.findAll();
	}

	public String saveProduct(ProductDTO productDTO) {
		try {
			ProductEntity productEntity = new ProductEntity();
			productEntity.setProductName(productDTO.getProductName());
			productEntity.setProductSubName(productDTO.getProductSubName());
			productEntity.setProductCode(productDTO.getProductCode());
			productEntity.setProductSubCode(productDTO.getProductSubCode());
			String productFinalCode = productDTO.getProductCode() + productDTO.getProductSubCode();
			productEntity.setProductFinalCode(productFinalCode);
			productRepo.save(productEntity);
			return "Success";

		} catch (Exception e) {
			e.printStackTrace();
			log.error("The product details are not saved", e.getMessage());
			return "Error";
		}
	}

	/*
	 * public String saveImage(byte[] imageData) { try { ProductImageEntity
	 * productImageEntity=new ProductImageEntity();
	 * productImageEntity.setImageData(imageData);
	 * productImageEntity=productImageRepo.save(productImageEntity); //String
	 * base64Image=Base64.getEncoder().encodeToString(imageData); return
	 * base64Image; } catch(Exception e) {
	 * log.error("There is unable to save image :{} ",e.getMessage()); return
	 * "Error"; } }
	 */

	public String getImageProduct(Long imageId) {

		try {

			ProductImageEntity productImageEntity = productImageRepo.findByImageId(imageId);
			if (productImageEntity != null) {
				byte[] image = productImageEntity.getImageData();
				String base64image = Base64.getEncoder().encodeToString(image);
				return base64image;
			} else {
				log.error("Image is not found : {} ", imageId);
				return "Image is not found";
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("There is unable to get the image of product {}", e.getMessage());
			return "Error";
		}

	}

	/*
	 * public ProductDTO2 findProduct(Long productId) { ProductDTO2 productDTO2 =
	 * new ProductDTO2(); try { Optional<ProductEntity> pro =
	 * productRepo.findByProductId(productId); if (pro.isPresent()) { ProductEntity
	 * data = pro.get(); productDTO2.setProductId(data.getProductId());
	 * productDTO2.setProductName(data.getProductName());
	 * productDTO2.setProductCode(data.getProductCode());
	 * 
	 * // Fetch the product image entity ProductImageEntity productImageEntity =
	 * data.getProductImageEntity();
	 * 
	 * if (productImageEntity != null && productImageEntity.getImageData() != null)
	 * { // If image data is already saved, encode it to Base64 byte[] imageData =
	 * productImageEntity.getImageData(); String base64Image =
	 * Base64.getEncoder().encodeToString(imageData);
	 * productDTO2.setProfileBase64Image(base64Image); } else { // If image data is
	 * not saved, save it and then encode it to Base64 // Assuming imageData is
	 * provided by some means byte[] imageData = ... // Provide your byte[] image
	 * data here productImageEntity = new ProductImageEntity();
	 * productImageEntity.setProductEntity(data);
	 * productImageEntity.setImageData(imageData);
	 * productImageRepo.save(productImageEntity); String base64Image =
	 * Base64.getEncoder().encodeToString(imageData);
	 * productDTO2.setProfileBase64Image(base64Image); }
	 * 
	 * return productDTO2; } else { log.error("Product with ID {} is not found",
	 * productId); return null; } } catch (Exception e) { e.printStackTrace();
	 * log.error("Please there is an unable to fetch the details for ID {}: {}",
	 * productId, e.getMessage()); return null; } }
	 */
	public static String byteImageToBase64(byte[] imageBytes) {
		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		return base64Image;
	}

	public ProductDTO2 saveProductDetails(ProductDTO2 productDTO2) {
		ProductEntity productEntity = mapper.map(productDTO2, ProductEntity.class);
		String base64Image = productDTO2.getProfileBase64Image();
		byte[] imageData = Base64.getDecoder().decode(base64Image);
		ProductImageEntity productImageEntity = new ProductImageEntity();
		productImageEntity.setImageData(imageData);
		productEntity.setProductImageEntity(productImageEntity);
		productEntity = productRepo.save(productEntity);
		return mapper.map(productEntity, ProductDTO2.class);
	}

	/*
	 * public ProductDTO2 getProductDetails(Long productId) { try { ProductEntity
	 * productEntity=productRepo.findById(productId); ProductDTO2
	 * productDTO2=mapper.map(productEntity, ProductDTO2.class);
	 * 
	 * }
	 * 
	 * }
	 */
	public ProductDTO2 getProductDetails(Long productId) {
		try {
			ProductEntity productEntity = productRepo.findById(productId)
					.orElseThrow(() -> new IllegalArgumentException("Product not found"));

			ProductDTO2 productDTO2 = mapper.map(productEntity, ProductDTO2.class);

			// Get the imageData from the productImageEntity
			ProductImageEntity productImageEntity = productEntity.getProductImageEntity();
			if (productImageEntity != null) {
				byte[] imageData = productImageEntity.getImageData();
				String base64Image = byteImageToBase64(imageData);
				productDTO2.setProfileBase64Image(base64Image);
			} else {
				log.warn("Product with ID {} does not have an associated image", productId);
				productDTO2.setProfileBase64Image(""); // Set an empty base64 string or handle as required
			}

			return productDTO2;
		} catch (Exception e) {
			log.error("Error occurred while fetching product details for ID {}: {}", productId, e.getMessage());
			throw new RuntimeException("Failed to fetch product details", e);
		}
	}

	/*
	 * public ProductDTO2 findProduct(Long productId) { ProductDTO2 productDTO2 =
	 * new ProductDTO2(); try { Optional<ProductEntity> pro =
	 * productRepo.findByProductId(productId); if (pro.isPresent()) { ProductEntity
	 * data = pro.get(); productDTO2.setProductId(data.getProductId());
	 * productDTO2.setProductName(data.getProductName());
	 * productDTO2.setProductCode(data.getProductCode()); ProductImageEntity
	 * productImageEntity = data.getProductImageEntity();
	 * convertProductImageEntityToDTO(productImageEntity, productDTO2); return
	 * productDTO2; } else { log.error("Product with ID {} is not found",
	 * productId); return null; } } catch (Exception e) { e.printStackTrace();
	 * log.error("Please there is an unable to fetch the details for ID {}: {}",
	 * productId, e.getMessage()); return null; } }
	 * 
	 * public ProductDTO2 convertProductImageEntityToDTO(ProductImageEntity
	 * productImageEntity, ProductDTO2 productDTO2) { // ProductDTO2 productDTO2 =
	 * new ProductDTO2(); if (productImageEntity != null) { byte[] imageBytes =
	 * productImageEntity.getImageData(); String profileBase64Image =
	 * Base64.getEncoder().encodeToString(imageBytes); //
	 * productDTO.setProductImage(null);
	 * productDTO2.setProfileBase64Image(profileBase64Image); } return productDTO2;
	 * } /* public void saveProductImage(Long productId, MultipartFile imageFile)
	 * throws IOException { Optional< ProductEntity> data =
	 * productRepo.findByProductId(productId);
	 *
	 * ProductImageEntity productImageEntity = new ProductImageEntity();
	 * productImageEntity.setProductEntity(data);
	 * productImageEntity.setImageData(imageFile.getBytes());
	 *
	 * productImageRepo.save(productImageEntity); }
	 */

}
