package com.sellerapp.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.JwtRequest;
import com.sellerapp.repository.GdmsApiRepository;

@Service
public class MobileService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MobileService.class);

	@Autowired
	GdmsApiRepository gdmsRepository;

	public static String byteImageToBase64(byte[] imageBytes) {
		// Encode the byte array to Base64
		String base64String = Base64.getEncoder().encodeToString(imageBytes);
		return base64String;
	}

	public String checkUserAndPassword(String mobileNumber, String pass) {
		try {
			Optional<GdmsApiUsers> userOptional = gdmsRepository.findByMobileNumber(mobileNumber);
			if (userOptional.isPresent()) {
				GdmsApiUsers user = userOptional.get();
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				if (passwordEncoder.matches(pass, user.getPassword())) {
					return "A";
				} else {
					return "NP";
				}
			} else {
				return "NA";
			}
		} catch (Exception e) {
			log.error("Exception occurred: " + e.getMessage());
			return "NA";
		}
	}

	public Integer otpGenerator(JwtRequest authenticationRequest) {
		try {
			String status = checkUserAndPassword(authenticationRequest.getMobileNumber(),
					authenticationRequest.getPassword());
			if (status.equals("A")) {
				Integer randNumber = ((int) (Math.random() * (9999 - 1000 + 1))) + 1000;

				System.out.println(randNumber);
				int updateStatus = gdmsRepository.updatetOtp(authenticationRequest.getMobileNumber(),
						randNumber.toString());
				if (updateStatus != 0) {
					log.info("Otp generated");

					Optional<GdmsApiUsers> userOptional = gdmsRepository
							.findByMobileNumber(authenticationRequest.getMobileNumber());
					if (userOptional.isPresent()) {
						GdmsApiUsers user = userOptional.get();
						authenticationRequest.setName(user.getName());
						authenticationRequest.setPassword(null);
						authenticationRequest.setProfileImage(null);
						String baseImg = null;

						if (user.getProfileImage() != null) {
							try {

								String base64ProfileImage = new String(user.getProfileImage(), StandardCharsets.UTF_8);
								authenticationRequest.setBaseImg(base64ProfileImage);

							} catch (Exception e) {
								e.printStackTrace();
								log.error("Failed to convert profile image to Base64: ", e.getMessage());
							}
						}
					}

					return randNumber;
				} else {
					return 0;
				}
			} else if (status.equalsIgnoreCase("NP")) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.error("Exception:-" + e.getMessage());
			return 0;
		}
	}

	public Integer restOtp(JwtRequest authenticationRequest) {
		try {
			int updateStatus = gdmsRepository.updatetOtp(authenticationRequest.getMobileNumber(),
					authenticationRequest.getOtpgen());
			return updateStatus;
		} catch (Exception e) {
			log.error("Exception:-" + e.getMessage());
			return 0;
		}

	}

}
