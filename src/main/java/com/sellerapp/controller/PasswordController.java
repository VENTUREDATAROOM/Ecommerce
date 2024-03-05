package com.sellerapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.ChangePasswordDTO;
import com.sellerapp.model.EmailDTO;
import com.sellerapp.model.ForgetPasswordDTO;
import com.sellerapp.model.OtpSignDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.VerifyOtpDTO;
import com.sellerapp.service.EmailService;
import com.sellerapp.service.OtpService;
import com.sellerapp.service.PasswordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "PasswordRecovery-API")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	@Autowired
	OtpService otpService;

	@Autowired
	private EmailService emailService;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordController.class);

	@PutMapping(value = "/changePassword")
	@Operation(summary = " to change the password", description = "this api is used for change the password")
	public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		String re = passwordService.changePassword(changePasswordDTO);
		if ("Success".equals(re)) {
			return Response2.generateResponse("password update successfullly", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
	}

	@PostMapping(value = "/forgetPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "forget the password karne se otp jayega", description = "this api is used for forget the password yeh otp bhejaga")
	public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

		String r = passwordService.otpSignin(forgetPasswordDTO);

		if ("Success".equals(r)) {
			return Response2.generateResponse("Forget the password ", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Password is not there", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}

	@PostMapping(value = "/passwordVerifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "to verify otp through password", description = "this api is for used to verify password through otp using mobileNumber")
	public ResponseEntity<?> verifyPasswordotp(@RequestBody VerifyOtpDTO verifyOtpDTO) {
		String result = passwordService.verifyOtp(verifyOtpDTO);
		if ("Success".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.OK, "200");

		} else if ("Invalid OTP".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.BAD_REQUEST, "400");

		} else {
			return Response2.generateResponse(result, HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}

	@PostMapping(value = "/forgetPasswordByEmail")
	@Operation(summary = "otp sign in through email", description = "this api is used for send email")
	public ResponseEntity<?> sendEmail(@RequestBody OtpSignDTO otpsignDTO) {

		boolean emailExists = otpService.checkEmailExists(otpsignDTO.getEmail());
		if (!emailExists) {
			return Response2.generateResponse("Email does not exist", HttpStatus.BAD_REQUEST, "400");
		}
		String result = otpService.otpByEmail(otpsignDTO);

		if ("Success".equals(result)) {
			return Response2.generateResponse("Email sent successfully through OTP", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}

	@PostMapping(value = "/forgetPasswordByVerifyEmail")
	@Operation(summary = "verification  email", description = "this api is used to verify the email")
	public ResponseEntity<?> verificationEmail(@RequestBody EmailDTO emailDTO) {
		String email = emailDTO.getEmail();
		boolean verificationsuccess = sendVerificationEmail(email);
		if (verificationsuccess) {
			return Response2.generateResponse("Email verified successfully", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Email is not verified successfully", HttpStatus.BAD_REQUEST, "400");
		}

	}

	private boolean sendVerificationEmail(String email) {
		// String email=emailDto.getEmail();
		String subject = "Verify your email";
		String message = "<html>" + "<body>" + "<p>Hello,</p>" +

				"<p>You receive this message either because you recently applied to, registered on our website, or are considered as a potential candidate for a job offered through our portal.</p>"
				+

				"<p>Please validate your email address by clicking <a hrRef=\"YOUR_VALIDATION_LINK\">here</a> (please log in using your existing credentials).<br>"
				+ "This will take only a few seconds and is to make sure that the recruiters can safely reach you through email.</p>"
				+

				"<p>Kind regards,<br>" + "Recruitment Team<br>" + "Venture Consultancy Services, Lucknow</p>" +

				"</body>" + "</html>";

		// emailService.sendEmail(subject, message, email);
		boolean res = emailService.sendEmail(subject, message, email);
		return res;
	}

	@PostMapping("verifyAnOtp")
	@Operation(summary = "verify the otp through email")
	public ResponseEntity<?> verifyOtp(@Valid @RequestBody VerifyOtpDTO request) {
		String result = otpService.verifyOtp(request);

		if ("Success".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.OK, "200");
		} else if ("Invalid OTP".equals(result)) {
			return Response2.generateResponse(result, HttpStatus.BAD_REQUEST, "400");
		} else {
			return Response2.generateResponse(result, HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}

}
