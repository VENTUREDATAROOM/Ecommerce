package com.sellerapp.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.JwtRequest;
import com.sellerapp.model.LoginDTO;
import com.sellerapp.model.LoginResponse;
import com.sellerapp.repository.GdmsApiRepository;

@Service
public class MobileService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MobileService.class);

	@Autowired
	GdmsApiRepository gdmsRepository;

	public LoginResponse loginInService(LoginDTO loginData) {

		Optional<GdmsApiUsers> entityData = this.gdmsRepository.findByMobileNumber(loginData.getMobileNumber());
		if (entityData.isPresent()) {
			int randNumber = ((int) (Math.random() * (9999 - 1000 + 1))) + 1000;
			String nn = Integer.toString(randNumber);
			LoginResponse loginResponse = new LoginResponse();
			GdmsApiUsers userData = entityData.get();
			loginResponse.setMobileNumber(userData.getMobileNumber());
			loginResponse.setName(userData.getName());
			loginResponse.setOtpgen(nn);
			gdmsRepository.save(userData);

			String image = byteImageToBase64(userData.getProfileImage());
			loginResponse.setProfileImage(image);
			return loginResponse;

		} else {

			LoginResponse loginResponse = new LoginResponse();
			GdmsApiUsers userData = entityData.get();
			loginResponse.setName(userData.getName());
			loginResponse.setOtpgen(null);

			loginResponse.setProfileImage(null);
			return loginResponse;

		}

	}

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
								// byte[] profileImageBytes = user.getProfileImage();
								// if (profileImageBytes != null) {
								// byte[] originalImageBytes = user.getProfileImage();
								//
								// baseImg=user.get
								// baseImg = Base64.getEncoder().encodeToString(user.getProfileImage());
								// authenticationRequest.setBaseImg(baseImg);
								// byte[] profileImageData = user.getProfileImage();
								// String base64ProfileImage =
								// Base64.getEncoder().encodeToString(profileImageData);
								// authenticationRequest.setBaseImg(base64ProfileImage);
								String base64ProfileImage = new String(user.getProfileImage(), StandardCharsets.UTF_8);
								authenticationRequest.setBaseImg(base64ProfileImage);
								// } else {
								// log.warn("Failed to encode profile image to Base64 bytes");
								// }
								// if (authenticationRequest.getProfileImage() != null) {
								// authenticationRequest
								// .setContentType(user.getProfileImage().getContentType());
								// }
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

	/*
	 * public Integer otpGenerator(JwtRequest authenticationRequest) { try { String
	 * status = checkUserAndPassword(authenticationRequest.getMobileNumber(),
	 * authenticationRequest.getPassword()); if (status.equals("A")) { Integer
	 * randNumber = ((int) (Math.random() * (9999 - 1000 + 1))) + 1000;
	 *
	 * System.out.println(randNumber); int updateStatus =
	 * gdmsRepository.updatetOtp(authenticationRequest.getMobileNumber(),
	 * randNumber.toString()); if (updateStatus != 0) { log.info("Otp generated");
	 *
	 * Optional<GdmsApiUsers> userOptional = gdmsRepository
	 * .findByMobileNumber(authenticationRequest.getMobileNumber());
	 *
	 * if (userOptional.isPresent()) { GdmsApiUsers user = userOptional.get();
	 * authenticationRequest.setName(user.getName());
	 * authenticationRequest.setPassword(null);
	 * authenticationRequest.setProfileImage(null); String baseImg = null; // Set
	 * name and profile image in if (user.getProfileImage() != null) { try { if
	 * (authenticationRequest.getProfileImage() != null) { authenticationRequest
	 * .setContentType(authenticationRequest.getProfileImage().getContentType());
	 *
	 * byte[] profileImageBytes =
	 * Base64.getEncoder().encode(user.getProfileImage());
	 *
	 * if (profileImageBytes != null) { baseImg =
	 * Base64.getEncoder().encodeToString(profileImageBytes);
	 *
	 * authenticationRequest.setBaseImg(baseImg); } else {
	 * log.warn("Failed to encode profile image to Base64 bytes"); } }
	 *
	 * } catch (Exception e) { e.printStackTrace();
	 * log.error("Failed to convert profile image to Base64: " + e.getMessage()); }
	 *
	 * } }
	 *
	 * return randNumber; } else { return 0; } } else if
	 * (status.equalsIgnoreCase("NP")) { return 1; } else { return 0; } } catch
	 * (Exception e) { log.error("Exception:-" + e.getMessage()); return 0; } }
	 */

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
