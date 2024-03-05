package com.sellerapp.service;

import java.util.Base64;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.RegisterModel;
import com.sellerapp.repository.GdmsApiRepository;

@Service
public class RegistrationService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationService.class);

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	GdmsApiRepository gdmsRepository;

	@Autowired
	ModelMapper mapper;

	public RegisterModel registerUser(RegisterModel regDTO) {

		try {
			byte[] base64ProfileImage = Base64.getEncoder().encode(regDTO.getProfileImage().getBytes());
			String baseImg = Base64.getEncoder().encodeToString(regDTO.getProfileImage().getBytes());
			String userCode = generateUserCode(regDTO.getName(), regDTO.getMobileNumber());
			GdmsApiUsers ec = mapper.map(regDTO, GdmsApiUsers.class);
			ec.setPassword(bcryptEncoder.encode(ec.getPassword()));
			ec.setName(regDTO.getName());
			ec.setUserCode(userCode);
			ec.setEmail(regDTO.getEmail());
			ec.setProfileImage(base64ProfileImage);
			gdmsRepository.save(ec);
			regDTO.setContentType(regDTO.getProfileImage().getContentType());
			regDTO.setPassword(null);
			regDTO.setBaseImg(baseImg);
			regDTO.setProfileImage(null);

			return regDTO;
		} catch (Exception e) {
			log.error("there is an exception in  registring the user {} ", e.getMessage());
			return regDTO;
		}
	}

	public String findByMobile(RegisterModel regDTO) {

		try {
			Optional<GdmsApiUsers> mobileCheck = gdmsRepository.findByMobileNumber(regDTO.getMobileNumber());
			if (mobileCheck.isPresent()) {
				return "A";
			} else {
				return "NA";
			}

		} catch (Exception e) {
			log.error("there is an exception in  registring the user {} ", e.getMessage());
			return "A";
		}

	}

	public String findByEmail(RegisterModel regDTO) {

		try {
			Optional<GdmsApiUsers> emailCheck = gdmsRepository.findByEmail(regDTO.getEmail());
			if (emailCheck.isPresent()) {
				return "A";
			} else {
				return "NA";
			}
		} catch (Exception e) {
			log.error("there is an exception in registering the email {} ", e.getMessage());
			return "A";
		}
	}

	public String generateUserCode(String name, String mobileNumber) {
		if (mobileNumber.length() != 10) {
			log.info("mobile no was incorrect , thats why no user got registered");
			System.out.println("Mobile number is already have length 10 digits");

		}
		return (name.substring(0, 2).toUpperCase()) + mobileNumber;
	}

	public boolean checkEmailExists(String email) {
		return gdmsRepository.existsByEmail(email);
	}

}
