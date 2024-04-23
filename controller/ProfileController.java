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
import com.sellerapp.model.ProfileDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.service.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Profile-API")
public class ProfileController {
	
	@Autowired
	private GdmsApiRepository Gdmsrepo;

	
	@Autowired
	private ProfileService profileService;

	@PostMapping(value = "uploadProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "upload profiles through date of birth")
	public ResponseEntity<?> uploadProfilePic(@ModelAttribute ProfileDTO pro) {
		
		GdmsApiUsers userData = this.Gdmsrepo.findByUserCode(pro.getUserCode());
		if(userData==null) {
			return new ResponseWithObject().generateResponse("userCode not Valid ..!!",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}
		
		String res = profileService.saveProfile(pro);
		if ("Success".equals(res)) {
			return new ResponseEntity<String>("Profile details are saved",HttpStatus.OK);
			//return new ResponseWithObject().generateResponse("Profile details are saved", HttpStatus.OK, "200", pro);

		} else if ("Error".equals(res)) {
			return new ResponseWithObject().generateResponse("Profile photo and date of birth is not there",
					HttpStatus.BAD_REQUEST, "400", "");

		} else {
			return Response2.generateResponse("Profile details are not saved", HttpStatus.INTERNAL_SERVER_ERROR, "500");

		}
	}
}