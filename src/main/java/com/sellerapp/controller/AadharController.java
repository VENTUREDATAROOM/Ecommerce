package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;


import com.sellerapp.model.AadharDTO;

import com.sellerapp.model.Response2;
import com.sellerapp.service.AadharService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Aadhar-API")
public class AadharController {

	@Autowired
	AadharService aadharService;
	
	@PostMapping(value="/uploadAadhar",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@Operation(summary="upload aadhar card details")
	public ResponseEntity<?> uploadAadhar(@RequestBody AadharDTO aadhar)
	
	{
		String frontPagefilename= aadhar.getFrontPage().getOriginalFilename();
		String result=aadharService.saveAadharDetails(aadhar);
		if("Success".equals(result))
		{
			return Response2.generateResponse("Aaadhar card  front details are get upload", HttpStatus.OK, "200");
		}
		
		else
		{
			return Response2.generateResponse("Aadhar Card front details are not upload", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}

	}
	@PostMapping(value ="/verifyAadharCard",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="verify aadhar card number")
	public ResponseEntity<?> verifyAadharcardNumber(@RequestBody AadharDTO aadhar) {
		boolean isValid = aadharService.verifyAadharNumber(aadhar);
		if (isValid) {
			return Response2.generateResponse("Aadhar details are verified", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Invalid Aadhar card number", HttpStatus.BAD_REQUEST, "400");
		}
	}
}

