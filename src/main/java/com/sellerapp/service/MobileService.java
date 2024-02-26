package com.sellerapp.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.repository.GdmsApiRepository;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.model.JwtRequest;
//import com.sellerapp.repository.GdmsApiRepository;
import java.lang.String;
import java.util.Optional;



@Service
public class MobileService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MobileService.class);

	@Autowired
    GdmsApiRepository gdmsRepository;
	
	
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
				Integer randNumber = ((int) (Math.random() * (9999 - 1000+1))) + 1000;
				//System.out.println(randNumber.toString().length());
				//if (randNumber.toString().length() < 4) {
				//	randNumber = randNumber + 1000;
				//}
				System.out.println(randNumber);
				int updateStatus = gdmsRepository.updatetOtp(authenticationRequest.getMobileNumber(), randNumber.toString());
				if (updateStatus != 0) {
					log.info("Otp generated");
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
			int updateStatus = gdmsRepository.updatetOtp(authenticationRequest.getMobileNumber(), "000000");
			return updateStatus;
		} catch (Exception e) {
			log.error("Exception:-" + e.getMessage());
			return 0;
		}

	}

	
}
