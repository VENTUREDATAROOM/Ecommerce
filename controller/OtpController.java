/*
 * package com.sellerapp.controller;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.sellerapp.model.Response2; import
 * com.sellerapp.model.VerifyOtpDTO; import com.sellerapp.service.EmailService;
 * import com.sellerapp.service.OtpService;
 * 
 * import io.swagger.v3.oas.annotations.Operation; import
 * io.swagger.v3.oas.annotations.tags.Tag;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "*")
 * 
 * @RequestMapping("api")
 * 
 * @Tag(name = "EmailOTP-API") public class OtpController {
 * 
 * private org.slf4j.Logger log =
 * org.slf4j.LoggerFactory.getLogger(OtpController.class);
 * 
 * @Autowired OtpService otpService;
 * 
 * @Autowired private EmailService emailService;
 * 
 * @PostMapping("verifyAnOtp")
 * 
 * @Operation(summary = "verify the otp through email") public ResponseEntity<?>
 * verifyOtp(@Valid @RequestBody VerifyOtpDTO request) { String result =
 * otpService.verifyOtp(request);
 * 
 * if ("Success".equals(result)) { return Response2.generateResponse(result,
 * HttpStatus.OK, "200"); } else if ("Invalid OTP".equals(result)) { return
 * Response2.generateResponse(result, HttpStatus.BAD_REQUEST, "400"); } else {
 * return Response2.generateResponse(result, HttpStatus.INTERNAL_SERVER_ERROR,
 * "500"); } }
 * 
 * /*
 *
 * @PostMapping(value = "/forgetPasswordByEmail")
 *
 * @Operation(summary = "otp sign in through email", description =
 * "this api is used for send email") public ResponseEntity<?>
 * sendEmail(@RequestBody OtpSignDTO otpsignDTO) {
 *
 * // log.info("Received otpsignDto : {} {} {} //
 * ",otpsignDto.getUsername(),otpsignDto.getEmail()); String result =
 * otpService.otpByEmail(otpsignDTO);
 *
 * if ("Success".equals(result)) { return
 * Response2.generateResponse("Email sent successfully through OTP",
 * HttpStatus.OK, "200"); } else { return
 * Response2.generateResponse("Failed to send email",
 * HttpStatus.INTERNAL_SERVER_ERROR, "500"); } }
 */

/*
 * @PostMapping(value = "/forgetPasswordByVerifyEmail")
 * 
 * @Operation(summary = "verification  email", description =
 * "this api is used toverify the email") public ResponseEntity<?>
 * verificationEmail(@RequestBody EmailDTO emailDTO) { String email =
 * emailDTO.getEmail(); boolean verificationsuccess =
 * sendVerificationEmail(email); if (verificationsuccess) { return
 * Response2.generateResponse("Email verified successfully", HttpStatus.OK,
 * "200"); } else { return
 * Response2.generateResponse("Email is not verified successfully",
 * HttpStatus.BAD_REQUEST, "400"); }
 * 
 * }
 * 
 * private boolean sendVerificationEmail(String email) { // String
 * email=emailDto.getEmail(); String subject = "Verify your email"; String
 * message = "<html>" + "<body>" + "<p>Hello,</p>" +
 * 
 * "<p>You receive this message either because you recently applied to, registered on our website, or are considered as a potential candidate for a job offered through our portal.</p>"
 * +
 * 
 * "<p>Please validate your email address by clicking <a hrRef=\"YOUR_VALIDATION_LINK\">here</a> (please log in using your existing credentials).<br>"
 * +
 * "This will take only a few seconds and is to make sure that the recruiters can safely reach you through email.</p>"
 * +
 * 
 * "<p>Kind regards,<br>" + "Recruitment Team<br>" +
 * "Venture Consultancy Services, Lucknow</p>" +
 * 
 * "</body>" + "</html>";
 * 
 * // emailService.sendEmail(subject, message, email); boolean res =
 * emailService.sendEmail(subject, message, email); return res; }
 */
