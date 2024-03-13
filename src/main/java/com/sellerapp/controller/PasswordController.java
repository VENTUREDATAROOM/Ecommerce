package com.sellerapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.ChangePasswordDTO;
import com.sellerapp.model.EmailDTO;
import com.sellerapp.model.ForgetPasswordDTO;
import com.sellerapp.model.OtpSignDTO;
import com.sellerapp.model.ResetPasswordDTO;
import com.sellerapp.model.Response2;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.model.VerifyOtpDTO;
import com.sellerapp.repository.GdmsApiRepository;
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

	@Autowired
	private GdmsApiRepository gdmsRepository;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordController.class);

	@PutMapping(value = "/changePassword")
	@Operation(summary = " to change the password", description = "this api is used for change the password")
	public ResponseEntity<?> changeToPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		String re = passwordService.changePassword(changePasswordDTO);
		if ("Success".equals(re)) {
			return Response2.generateResponse("password update successfullly", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Error", HttpStatus.BAD_REQUEST, "400");
		}
	}

	@PostMapping(value = "/resetPassword")
	@Operation(summary = "to reset the password", description = "this api is for reset the password")
	public ResponseEntity<?> resetToPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		String e = passwordService.resetPassword(resetPasswordDTO);
		if ("Success".equals(e)) {
			return Response2.generateResponse("Password reset successfully", HttpStatus.OK, "200");
		} else {
			return Response2.generateResponse("Password cannot resend ", HttpStatus.BAD_REQUEST, "400");
		}

	}

	@PostMapping(value = "/forgetByMob")
	@Operation(summary = "forget the password karne se otp jayega", description = "this api is used for forget the password yeh otp bhejaga")
	public ResponseEntity<Object> forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

		String otp = this.passwordService.otpBySign(forgetPasswordDTO);

		if (otp.equals("User not found") || otp.equals("Error")) {
			return new ResponseWithObject().generateResponse("Error occurred: " + otp, HttpStatus.BAD_REQUEST, "400",
					"");
		} else {
			ForgetPasswordDTO responseDTO = new ForgetPasswordDTO();
			responseDTO.setUserCode(forgetPasswordDTO.getUserCode());
			responseDTO.setOtp(otp);
			Optional<GdmsApiUsers> userOptional = Optional
					.ofNullable(this.gdmsRepository.findByUserCode(forgetPasswordDTO.getUserCode()));
			if (userOptional.isPresent()) {
				responseDTO.setUsername(userOptional.get().getMobileNumber());
			} else {
				return new ResponseWithObject().generateResponse("User not found", HttpStatus.NOT_FOUND, "404", "");
			}

			return new ResponseWithObject().generateResponse("Forget the password", HttpStatus.OK, "200", responseDTO);
		}
	}

	@PostMapping(value = "/forgetByEmail")
	@Operation(summary = "otp sign in through email")
	public ResponseEntity<Object> sendEmail(@RequestBody OtpSignDTO otpSignDTO) {

		String otp = otpService.otpByUserCode(otpSignDTO);
		if (otp.equals("User not found") || otp.equals("Error")) {
			return new ResponseWithObject().generateResponse("Error occured ", HttpStatus.BAD_REQUEST, "400", "");
		}

		else {
			OtpSignDTO response = new OtpSignDTO();
			response.setUserCode(otpSignDTO.getUserCode());

			response.setOtp(otp);
			Optional<GdmsApiUsers> userOptional = Optional
					.ofNullable(this.gdmsRepository.findByUserCode(otpSignDTO.getUserCode()));
			if (userOptional.isPresent()) {
				response.setEmail(userOptional.get().getEmail());
			} else {
				return new ResponseWithObject().generateResponse("User not found", HttpStatus.NOT_FOUND, "404", "");
			}

			return new ResponseWithObject().generateResponse("Email sent successfully through OTP", HttpStatus.OK,
					"200", response);
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

		String subject = "Verify your email";
		String message = "<html>" + "<body>" + "<p>Hello,</p>" +

				"<p>You receive this message either because you recently applied to, registered on our website, or are considered as a potential candidate for a job offered through our portal.</p>"
				+

				"<p>Please validate your email address by clicking <a hrRef=\"YOUR_VALIDATION_LINK\">here</a> (please log in using your existing credentials).<br>"
				+ "This will take only a few seconds and is to make sure that the recruiters can safely reach you through email.</p>"
				+

				"<p>Kind regards,<br>" + "Recruitment Team<br>" + "Venture Consultancy Services, Lucknow</p>" +

				"</body>" + "</html>";

		boolean res = emailService.sendEmail(subject, message, email);
		return res;
	}

	@PostMapping("verifyAnOtp")
	@Operation(summary = "this  api is to verify otp by userCode")
	public ResponseEntity<?> verifyByOtp(@RequestBody VerifyOtpDTO verifyOtpDTO) {
		String result = passwordService.verifyOtp(verifyOtpDTO);

		if ("Success".equals(result)) {
			return Response2.generateResponse("Otp verified successfully through user code", HttpStatus.OK, "200");
		} else if ("Error".equals(result) || "User not found".equals(result) || "No OTP found".equals(result)) {
			return Response2.generateResponse("There is an error for verify otp through usercode",
					HttpStatus.BAD_REQUEST, "400");
		} else {
			return Response2.generateResponse("There is wrong otp through usercode", HttpStatus.INTERNAL_SERVER_ERROR,
					"500");
		}
	}

}

