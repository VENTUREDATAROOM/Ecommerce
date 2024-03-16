package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.AadharDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.repository.GdmsApiRepository;
//import com.sellerapp.model.AadharDTO;
//import com.sellerapp.model.Response2;
import com.sellerapp.service.AadharService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Aadhar-API")
public class AadharController {
	
	
	@Autowired
	private GdmsApiRepository Gdmsrepo;

	@Autowired
	AadharService aadharService;

	@PostMapping(value = "/uploadAadhar", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)

	@Operation(summary = "upload aadhar card details")
	public ResponseEntity<?> uploadAadhar(@ModelAttribute AadharDTO aadhar)

	{
		
		GdmsApiUsers userData = this.Gdmsrepo.findByUserCode(aadhar.getUserCode());
		if(userData==null) {
			return new ResponseWithObject().generateResponse("userCode not Valid ..!!",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}
		

		if (!aadharService.verifyAadharNumber(aadhar)) {
			return Response2.generateResponse("Invalid aadhar number", HttpStatus.BAD_REQUEST, "400");
		}
		String result = aadharService.saveAadharDetails(aadhar);
		if ("Success".equals(result)) {
			return new ResponseWithObject().generateResponse("Aaadhar card details are get upload", HttpStatus.OK,
					"200", aadhar);
		}

		else {
			return new ResponseWithObject().generateResponse("Aadhar Card  details are not upload",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}

	}
}