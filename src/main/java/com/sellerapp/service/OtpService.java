package com.sellerapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.entity.OtpEntity;
import com.sellerapp.model.OtpSignDTO;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.repository.UserRepository;

@Service
public class OtpService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OtpService.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Autowired
	private GdmsApiRepository gdmsRepository;
	@Autowired
	ModelMapper mapper;

	
	public String otpByUserCode(OtpSignDTO otpSignDTO) {
		try {
			String userCode = otpSignDTO.getUserCode();
			if (userCode == null) {
				return "Usercode is required";
			} else {
				GdmsApiUsers t = this.gdmsRepository.findByUserCode(userCode);
				if (t != null) {
					// GdmsApiUsers t = userOptional.get();
					String otp = generateRandomOtp();

					OtpEntity oe = new OtpEntity();
					oe.setUserCode(userCode);
					oe.setEmail(t.getEmail());
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

					sendOtpByEmail(t.getEmail(), otp);

					log.info("Otp sign in: {}{}", otpSignDTO.getUserCode());

					return otp;
				} else {
					log.error("User is not found with userCode:{}", otpSignDTO.getUserCode());
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in otpByUserCode: {}", e.getMessage());
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

	/*
	 * public boolean checkEmailExists(OtpSignDTO otpSignDTO) { return
	 * userRepository.existsByEmail(otpSignDTO); }
	 */

}
