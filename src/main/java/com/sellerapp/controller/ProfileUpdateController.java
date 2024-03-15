package com.sellerapp.controller;

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

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.repository.GdmsApiRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/update")
public class ProfileUpdateController {
	
	@Autowired
	private GdmsApiRepository userRepo;
	
	
	
	
	@GetMapping("/get/UserData")
	public ResponseEntity<?> getDataOfUser(@RequestParam("UserCode") String userCode){
		
		try {	
			GdmsApiUsers data = this.userRepo.findByUserCode(userCode);
			return new ResponseEntity<GdmsApiUsers>(data,HttpStatus.OK);
			
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

}
