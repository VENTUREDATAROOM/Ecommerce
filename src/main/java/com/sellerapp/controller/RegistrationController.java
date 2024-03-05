package com.sellerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.OtpSignDTO;
import com.sellerapp.model.RegisterModel;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.service.OtpService;
import com.sellerapp.service.RegistrationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "Registration-API")
public class RegistrationController {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private OtpService otpService;

	@PostMapping(value = "/createAccount", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, "image/jpeg",
			"image/png" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registration(@ModelAttribute RegisterModel regDTO) {
		try {
			String mobileStatus = registrationService.findByMobile(regDTO);

			if ("A".equals(mobileStatus)) {
				return Response2.generateResponse("Mobile Number already available", HttpStatus.OK, "201");
			} else {
				String emailStatus = registrationService.findByEmail(regDTO);
				if ("A".equals(emailStatus)) {
					return Response2.generateResponse("Email already registered", HttpStatus.OK, "201");
				} else if ("NA".equals(emailStatus)) {
					// Check if the email is valid before sending OTP
					boolean isValidEmail = isValidEmail(regDTO.getEmail());
					if (!isValidEmail) {
						return Response2.generateResponse("Invalid email address", HttpStatus.BAD_GATEWAY, "502");
					}
					boolean emailExists = registrationService.checkEmailExists(regDTO.getEmail());
					if (emailExists) {
						return Response2.generateResponse("Email address already  existed", HttpStatus.BAD_REQUEST,
								"400");
					}

					// Generate and send OTP
					String otpResult = otpService.otpByEmail(new OtpSignDTO(regDTO.getEmail()));
					if ("Success".equals(otpResult)) {
						// Proceed with registration after sending OTP
						RegisterModel regResponse = registrationService.registerUser(regDTO);
						log.info("Registration status of the user: {}", regResponse);
						if (regResponse.getMobileNumber() != null && regResponse.getEmail() != null) {
							return new ResponseWithObject().generateResponse("Successfully registered", HttpStatus.OK,
									"200", regResponse);
						} else {
							return new ResponseWithObject().generateResponse("Failed to register the user",
									HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
						}
					} else {
						return Response2.generateResponse("Failed to send OTP to email",
								HttpStatus.INTERNAL_SERVER_ERROR, "500");
					}
				}
			}
			return Response2.generateResponse("Unknown error occurred during registration",
					HttpStatus.INTERNAL_SERVER_ERROR, "500");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred during registration: {}", e.getMessage());
			return Response2.generateResponse("Error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR,
					"500");
		}
	}

	private boolean isValidEmail(String email) {

		return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

}
