/*package com.sellerapp.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.ProductEntity;
import com.sellerapp.model.ProductDTO;
import com.sellerapp.model.ProductDTO2;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Product-API")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getAllProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductEntity> getAllProducts() {
		return productService.getAllProduct();
	}

	@PostMapping(value = "/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
		String res = productService.saveProduct(productDTO);
		if ("Success".equals(res)) {
			return Response2.generateResponse("Product details are successfully saved", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Product details are not saved properly", HttpStatus.BAD_REQUEST, "400");
		}
	}

	@GetMapping(value = "/getImageProduct/{imageId}")
	public ResponseEntity<?> getImageOfProduct(@PathVariable Long imageId) {
		String base64image = productService.getImageProduct(imageId);
		if (base64image != null) {
			return Response2.generateResponse(base64image, HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
	}

	/*
	 * @GetMapping(value = "/getProduct/{productId}") public ResponseEntity<?>
	 * getProduct(@PathVariable Long productId) { ProductDTO2 productDTO =
	 * productService.findProduct(productId); if (productDTO != null) { return new
	 * ResponseWithObject().generateResponse("provide", HttpStatus.OK, "200",
	 * productDTO); } else { return new
	 * ResponseWithObject().generateResponse("not provide",
	 * HttpStatus.INTERNAL_SERVER_ERROR, "500", productDTO); }
	 *
	 * }
	 */
	/*@GetMapping(value = "/getProduct/{productId}")
	public ResponseEntity<?> getProductDetails(@PathVariable Long productId) {
		ProductDTO2 productDTO2 = productService.getProductDetails(productId);
		if (productDTO2 != null) {
			return new ResponseWithObject().generateResponse("provide", HttpStatus.OK, "200", productDTO2);
		} else {
			return new ResponseWithObject().generateResponse("not provide", HttpStatus.INTERNAL_SERVER_ERROR, "500",
					productDTO2);
		}

	}

	/*
	 * @PostMapping(value = "/saveProductDetails", consumes = {
	 * MediaType.MULTIPART_FORM_DATA_VALUE }, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?>
	 * saveProductDetails(@RequestBody ProductDTO2 productDTO2,
	 *
	 * @RequestParam("imageData") MultipartFile imageData) { ProductDTO2
	 * saveProductDTO = productService.saveProductDetails(productDTO2,imageData);
	 * return new
	 * ResponseWithObject().generateResponse("Product details are saved ",
	 * HttpStatus.OK, "200", saveProductDTO);
	 *
	 * }
	 */
	/*@PostMapping(value = "/saveProductDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveProductDetails(@ModelAttribute ProductDTO2 productDTO2,
			@RequestParam("imageData") MultipartFile imageData) {
		try {

			if (imageData == null || imageData.isEmpty()) {
				return new ResponseWithObject().generateResponse("Image data is missing", null, null, imageData);
			}

			byte[] imageBytes = imageData.getBytes();

			productDTO2.setProfileBase64Image(Base64.getEncoder().encodeToString(imageBytes));

			ProductDTO2 savedProduct = productService.saveProductDetails(productDTO2);

			return new ResponseWithObject().generateResponse("Product details are saved", HttpStatus.OK, "200",
					savedProduct);
		} catch (Exception e) {
			return new ResponseWithObject().generateResponse("Error occurred while saving product details",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}
	}
}*/
