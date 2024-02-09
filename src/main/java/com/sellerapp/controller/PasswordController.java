package com.sellerapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.model.ForgetPasswordDto;
import com.sellerapp.model.ResetPasswordDto;
import com.sellerapp.model.Response2;
import com.sellerapp.model.VerifyOtpDto;
import com.sellerapp.service.PasswordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "ForgetPassword-API")
public class PasswordController {

	@Autowired
	private PasswordService passwordService;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordController.class);
	@PostMapping(value="/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetpasswordDto)
	{
		String re=passwordService.resetpassword(resetpasswordDto);
		if("Success".equals(re))
		{
			return Response2.generateResponse("password reset successfullly",HttpStatus.OK, "200");
		}
		else
		{
			return  Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
	}
	@PostMapping(value="/forgetPassword")
	@Operation(summary="forget the password ")
	public ResponseEntity<?> forgetPassword( @RequestBody ForgetPasswordDto forgetpasswordDto) {

		System.out.println("Received ForgetPassword is  :" +forgetpasswordDto.getPassword());

		String r = passwordService.otpSignin(forgetpasswordDto);

		if ("Success".equals(r)) {
			return Response2.generateResponse("Forget the password ", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Password is not there", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		}
	}
	@PostMapping(value="/passwordVerifyotp")
	@Operation(summary="to verify otp through password")
	public ResponseEntity<?>  verifyPasswordotp(@RequestBody VerifyOtpDto verifyotpDto)
	{
		String result=passwordService.verifyOtp(verifyotpDto);
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
