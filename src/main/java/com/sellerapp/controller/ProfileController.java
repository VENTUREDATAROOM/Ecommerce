package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.sellerapp.model.ProfileDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.service.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Profile-API")
public class ProfileController {
    @Autowired
	private ProfileService profileService;
   
	@PostMapping(value="uploadProfile",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="upload profiles through date of birth")
	public ResponseEntity<?>  uploadProfilePic(@RequestBody  ProfileDTO pro)
	{
		   String res=profileService.saveProfile(pro);
		if("Success".equals(res))
		{
			return Response2.generateResponse("Profile details are saved", HttpStatus.OK,"200");
			
		}
		else if("Error".equals(res))
		{
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
			
		}
		else
		{
			return Response2.generateResponse("Profile details are not saved", HttpStatus.INTERNAL_SERVER_ERROR, "500");
			
		}
	}
}
