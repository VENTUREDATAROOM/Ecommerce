package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.PancardDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.service.PancardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Pancard-API")
public class PancardController {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PancardController.class);

	@Autowired
	PancardService pancardService;
	
	
	@Autowired
	private GdmsApiRepository Gdmsrepo;

	
	@PostMapping(value = "/uploadPancard", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)

	@Operation(summary = "upload pancard details")
	public ResponseEntity<?> uploadPan(@ModelAttribute PancardDTO pan)

	{
		

		GdmsApiUsers userData = this.Gdmsrepo.findByUserCode(pan.getUserCode());
		if(userData==null) {
			return new ResponseWithObject().generateResponse("userCode not Valid ..!!",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}

		if (!pancardService.verifyPancardNumber(pan)) {
			return Response2.generateResponse("Invalid pancard number", HttpStatus.BAD_REQUEST, "400");
		}
		String result = pancardService.savePancardPhoto(pan);
		if ("Success".equals(result)) {
			return new ResponseWithObject().generateResponse("Pancard   details are get upload", HttpStatus.OK, "200",
					pan);
		}

		else {
			return new ResponseWithObject().generateResponse("Pancard  details are not upload",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}

	}

	

}