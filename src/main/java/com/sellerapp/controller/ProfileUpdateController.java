package com.sellerapp.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.AadharEntity;
import com.sellerapp.entity.BankDetailsEntity;
import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.entity.PancardEntity;
import com.sellerapp.entity.ProfileEntity;
import com.sellerapp.model.ProfileGetDTO;
import com.sellerapp.repository.AadharRepository;
import com.sellerapp.repository.BankRepo;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.repository.PancardRepo;
import com.sellerapp.repository.ProfileRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/update")
public class ProfileUpdateController {
	
	@Autowired
	private GdmsApiRepository userRepo;
	
	
	@Autowired
	private AadharRepository aadharRepo;
	
	@Autowired
	private PancardRepo pancardRepo;
	
	@Autowired
	private BankRepo bankRepo;
	
	
	@Autowired
	private ProfileRepo profileRepo;
	
	
	
	
	
	
	
	@GetMapping("/get/UserData")
	public ResponseEntity<?> getDataOfUser(@RequestParam("UserCode") String userCode){
		
		
		try {	
			
			AadharEntity adharData = this.aadharRepo.findByUserCode(userCode);
			PancardEntity panCardData = this.pancardRepo.findByUserCode(userCode);
			BankDetailsEntity bankData = this.bankRepo.findByUserCode(userCode);
			ProfileEntity profileData = this.profileRepo.findByUserCode(userCode);
			GdmsApiUsers data = this.userRepo.findByUserCode(userCode);
			
			ProfileGetDTO ResponseData = new ProfileGetDTO();
			
			if(adharData!=null) {
				ResponseData.setAadharNumber(adharData.getAadharNumber());
			}
			if(panCardData!=null) {
				ResponseData.setPanCardNumber(panCardData.getPancardNumber());
			}
			
			if(profileData!=null) {	
				ResponseData.setDateOfBirth(profileData.getDateofbirth());
			}
			
			ResponseData.setUserCode(data.getUserCode());
			ResponseData.setImage( byteImageToBase64(data.getProfileImage()));
			ResponseData.setMobileNumber(data.getMobileNumber());
			ResponseData.setName(data.getName());
			ResponseData.setEmail(data.getEmail());
			ResponseData.setUserCode(userCode);
			
			if(adharData!=null) {
				ResponseData.setProfileCompletion("40%");
				if(panCardData!=null) {
					ResponseData.setProfileCompletion("60%");
					if(bankData!=null) {
						ResponseData.setProfileCompletion("80%");
						if(profileData!=null) {
							ResponseData.setProfileCompletion("100%");
						}
					}
				}
			}
			
			return new ResponseEntity<ProfileGetDTO>(ResponseData,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Bad Request",HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	
	
	@PostMapping(value="/UserUpdateProfile",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateUserProfile(@RequestParam("MobileNumber") String mobileNumber,
			@RequestParam("UserCode") String userCode,
			@RequestParam("file") MultipartFile FileImage){
		try {
			GdmsApiUsers data = this.userRepo.findByUserCode(userCode);
			
			data.setMobileNumber(mobileNumber);
			data.setProfileImage(FileImage.getBytes());
			
			GdmsApiUsers UserData = this.userRepo.save(data);
			if(UserData==null) {
				throw new NullPointerException("Opps! data not Inserted");
			}
			
			return new ResponseEntity<String>("User Profile Updated SuccessFully!",HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<String>("Opps! SomeThing wrong..! ",HttpStatus.BAD_GATEWAY);
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

}
