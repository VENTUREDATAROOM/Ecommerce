package com.sellerapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.OtpEntity;
import com.sellerapp.model.OtpSignDTO;
import com.sellerapp.model.VerifyOtpDTO;
import com.sellerapp.repository.UserRepository;

@Service
public class OtpService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OtpService.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	ModelMapper mapper;

	public String otpByEmail(OtpSignDTO otpsignDTO) {
		try {
			// String userCode = otpsignDto.getUserCode();
			// String email = otpsignDto.getEmail();
			// String username = otpsignDto.getUsername();

			String otp = generateRandomOtp();

			OtpEntity oe = new OtpEntity();

			// oe.setUsername(otpsignDTO.getUsername());
			oe.setEmail(otpsignDTO.getEmail());

			oe.setOtp(otp);
			LocalDateTime currentDateTime = LocalDateTime.now();
			LocalDateTime otpSendStringFormatted = currentDateTime.plusMinutes(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpSendString = otpSendStringFormatted.format(formatter);

			oe.setOtpSend(otpSendString);
			LocalDateTime currentdateTime = LocalDateTime.now();
			LocalDateTime otpExpiryStringFormatted = currentdateTime.plusMinutes(1);

			DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpExpiryString = otpExpiryStringFormatted.format(formater);

			oe.setOtpExpiry(otpExpiryString);
			oe = userRepository.save(oe);

			sendOtpByEmail(otpsignDTO.getEmail(), otp);

			// sendVerificationEmail(email);

			log.info("Otp sign in " + otpsignDTO.getEmail());
			return "Success";
		} catch (Exception e) {
			log.error("Error in otpsign: " + e.getMessage());
			return "Error";
		}
	}

	public String verifyOtp(VerifyOtpDTO request)

	{
		try {
			Optional<OtpEntity> otpOptional = userRepository.findByUsernameAndOtp(request.getUsername(),
					request.getOtp());
			if (otpOptional.isPresent()) {
				OtpEntity oe = otpOptional.get();
				if (request.getOtp().equals(oe.getOtp()) && oe.getOtpExpiry() != null) {
					oe.setOtp(request.getOtp());
					String otpexpiry = oe.getOtpExpiry();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime otpExpiry = LocalDateTime.parse(otpexpiry, formatter);

					LocalDateTime currentDateTime = LocalDateTime.now();
					if (otpExpiry.isAfter(currentDateTime)) {

						System.out.println("otpExpiry get expire after the current time");
						return "Success";
					} else if (otpExpiry.isBefore(currentDateTime)) {
						System.out.println("otpexpiry get expire before the current time");
						return "Invalid OTP";
					} else {

						System.out.println("otpExpiry is not equal to current DateTime");

					}
					oe.setOtpExpiry(otpexpiry);
					userRepository.saveAndFlush(oe);
					System.out.println("Verify otp" + request.getUsername() + "," + request.getOtp());
					log.info("Verify otp : " + request.getUsername() + ", " + request.getOtp());
					return "Success";
				} else {
					return "It is wrong";
				}
			} else {
				return "User is not found";
			}
		} catch (Exception e) {
			log.error("Error in sign in for otp" + e.getMessage());
			return "Error";
		}
	}

	private String generateRandomOtp() {

		Random random = new Random();
		int otpValue = 1000 + random.nextInt(9000);
		return String.valueOf(otpValue);
	}

	private void sendOtpByEmail(String email, String otp) {

		String subject = "OTP verification";
		String message = "<html><body>" + "<p>Dear User,</p>" + "<p>Your OTP for verification is: <strong>" + otp
				+ "</strong></p>" + "<p>Please use this OTP within 1 minute to complete your verification process.</p>"
				+ "<p>If you didn't request this OTP, please ignore this email.</p>" + "<p>Best regards,</p>"
				+ "<p>Venture Consulting Services</p>" + "</body></html>";

		emailService.sendEmail(subject, message, email);
	}

	public boolean checkEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	/*
	 * private boolean sendVerificationEmail(EmailDto emailDto) { String
	 * email=emailDto.getEmail(); String subject = "Verify your email"; String
	 * message = "<html>" + "<body>" + "<p>Hello,</p>" +
	 *
	 * "<p>You receive this message either because you recently applied to, registered on our website, or are considered as a potential candidate for a job offered through our portal.</p>"
	 * +
	 *
	 * "<p>Please validate your email address by clicking <a href=\"YOUR_VALIDATION_LINK\">here</a> (please log in using your existing credentials).<br>"
	 * +
	 * "This will take only a few seconds and is to make sure that the recruiters can safely reach you through email.</p>"
	 * +
	 *
	 * "<p>Kind regards,<br>" + "Recruitment Team<br>" +
	 * "Venture Consultancy Services, Lucknow</p>" +
	 *
	 * "</body>" + "</html>";
	 *
	 * //emailService.sendEmail(subject, message, email); boolean
	 * res=emailService.sendEmail(subject, message, email); return res; }
	 */
}
