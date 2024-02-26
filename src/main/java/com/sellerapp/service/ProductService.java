package com.sellerapp.service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	private ProductImageRepo productImageRepo;
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductService.class);
	
	public List<ProductEntity> getAllProduct()
	{
		return productRepo.findAll();
	}
	public String saveProduct(ProductDTO productDTO)
	{
	try
	{
	   ProductEntity productEntity=new ProductEntity();
	   productEntity.setProductName(productDTO.getProductName());
	   productEntity.setProductSubName(productDTO.getProductSubName());
	   productEntity.setProductCode(productDTO.getProductCode());
	   productEntity.setProductSubCode(productDTO.getProductSubCode());
	   String productFinalCode=productDTO.getProductCode()+productDTO.getProductSubCode();
	   productEntity.setProductFinalCode(productFinalCode);
	   productRepo.save(productEntity);
		return "Success";
		
	} catch(Exception e)
	  {
		e.printStackTrace();
		log.error("The product details are not saved",e.getMessage());
		return "Error";
	  }
	}
	
	
	public String getImageProduct(Long imageId)
	{
		
		try
		{
	
		ProductImageEntity productImageEntity=productImageRepo.findByImageId(imageId);
		//MultipartFile productImageFile=productImageEntity.getProductImage();
		byte[] image=productImageEntity.getImageData();
	    String base64image=Base64.getEncoder().encodeToString(image);
		productImageEntity.setImageData(image);
		productImageRepo.save(productImageEntity);
		return base64image;
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("There is unable to get the image of product {}", e.getMessage());
			return "Error";
		}
		  
		
	}
	
	
	/*public ProductDTO2   findProduct(Long productId)
	
	{
		ProductDTO2 productDTO=new ProductDTO2();
		try
		{
			Optional<ProductEntity> productEntityOptional=productRepo.findByProductId(productId);
			if(productEntityOptional.isPresent())
			{
				ProductEntity productEntity=productEntityOptional.get();
			productDTO.setProductName(productEntity.getProductName());
			productDTO.setProductCode(productEntity.getProductCode());
			ProductImageEntity productImageEntity=productEntity.getProductImage();   // Assume one to one relationship
			 byte[] imageD=productImageEntity.getImageData();
			 ByteArrayInputStream inputStream=new ByteArrayInputStream(imageD);
			 MultipartFile multiFile = new MockMultipartFile("productImage", "productImage.jpg", "image/jpeg", inputStream);
	            
	            productDTO.setProductImage(multiFile);
	            
			
			return productDTO;
			}
			else
			{
				log.error("Product with ID {} is not found",productId);
				return null;
			}
		 } catch(Exception e)
		   {
		     e.printStackTrace();
		   log.error("Please there is an unable to fetch the details {} " ,e.getMessage());
		   return productDTO;
		  }
	}*/
	

}