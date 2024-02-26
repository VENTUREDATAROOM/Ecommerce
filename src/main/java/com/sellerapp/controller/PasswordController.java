package com.sellerapp.controller;



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

import com.sellerapp.model.ForgetPasswordDTO;
import com.sellerapp.model.ChangePasswordDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.VerifyOtpDTO;
import com.sellerapp.service.PasswordService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "ForgetPassword-API")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordController.class);
	@PutMapping(value="/changePassword")
	@Operation(summary=" to change the password",description="this api is used for change the password")
	public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDTO changePasswordDTO)
	{
		String re=passwordService.changePassword(changePasswordDTO);
		if("Success".equals(re))
		{
			return Response2.generateResponse("password update successfullly",HttpStatus.OK, "200");
		}
		else
		{
			return  Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
	}
	@PostMapping(value="/forgetPassword",consumes=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="forget the password", description="this api is used for forget the password")
	public ResponseEntity<?> forgetPassword( @RequestBody ForgetPasswordDTO forgetPasswordDTO) {

		//System.out.println("Received ForgetPassword is  :" +forgetpasswordDto.getPassword());

		String r = passwordService.otpSignin(forgetPasswordDTO);

		if ("Success".equals(r)) {
			return Response2.generateResponse("Forget the password ", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Password is not there", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}
	@PostMapping(value="/passwordVerifyotp",consumes=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to verify otp through password", description="this api is for used to verify password through otp using username")
	public ResponseEntity<?>  verifyPasswordotp(@RequestBody VerifyOtpDTO verifyOtpDTO)
	{
		String result=passwordService.verifyOtp(verifyOtpDTO);
		if("Success".equals(result))
		{
			return Response2.generateResponse(result, HttpStatus.OK, "200");

		}
		else if("Invalid OTP".equals(result))
		{
			return Response2.generateResponse(result, HttpStatus.BAD_REQUEST, "400");

		}
		else
		{
			return  Response2.generateResponse(result, HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}



}
