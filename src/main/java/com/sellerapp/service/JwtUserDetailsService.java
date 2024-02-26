package com.sellerapp.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
//import com.sellerapp.model.JwtRequest;
import com.sellerapp.entity.LoginHistoryEntity;
import com.sellerapp.model.JwtRequest;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.repository.LoginHistoryRepo;
import com.sellerapp.util.RequestUtil;
@Service
public class JwtUserDetailsService  implements UserDetailsService {

	@Autowired
	GdmsApiRepository gdmsRepository;
	
	@Autowired
	LoginHistoryRepo historyRepo;
	
	@Autowired
	RequestUtil requestUtil;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtUserDetailsService.class);


	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {

		try {
			//Optional<GdmsApiUsers> us = gdmsRepository.findByMobileNumber(mobileNumber);
			Optional<GdmsApiUsers> us = gdmsRepository.findByMobileNumber(mobileNumber);
			if (!us.isPresent()) {
				throw new UsernameNotFoundException("user is not present in the database  " + mobileNumber);

			} else {
				return new User(us.get().getMobileNumber(), us.get().getPassword(), new ArrayList<>());
			}

		} catch (Exception e) {
			throw new UsernameNotFoundException("something  went wrong exception is :" + e.getMessage());
		}

	}public UserDetails loadUserByOtp(String mobileNumber, String otp) throws UsernameNotFoundException {
		log.info("username with old or new check for dao request {}" , mobileNumber);
		try {
			Optional<GdmsApiUsers> user = gdmsRepository.findByMobileNumberAndOtp(mobileNumber, otp);
			log.info("response from database {} " , user.get().getMobileNumber());
			if (!user.isPresent()) {
				throw new UsernameNotFoundException("User not found with username: " + mobileNumber);
			} else {
				return new User(user.get().getMobileNumber(), user.get().getPassword(), new ArrayList<>());
			}

		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with username: " + mobileNumber);
		}

	}
	public void setloginHistory(JwtRequest auth, String token) {
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    LoginHistoryEntity historyEntity = new LoginHistoryEntity();
	    historyEntity.setUserId(auth.getMobileNumber());
	    historyEntity.setOtp(auth.getOtpgen());
	    historyEntity.setLoginTime(timestamp);
	    historyEntity.setFrmApp("ONLINE");
	    historyEntity.setToken(token);
	  
	    historyRepo.save(historyEntity);
	}
	
	public String getMobileNo(JwtRequest auth) {
	    try {
	        Optional<GdmsApiUsers> us = gdmsRepository.findByMobileNumber(auth.getMobileNumber());
	        if (us.isPresent()) {
	            String mobileNo = us.get().getMobileNumber().substring(0, 1) + "XXXXXXX" + us.get().getMobileNumber().substring(8, 10);
	            return mobileNo;
	        } else {
	            // Handle case where user is not found
	            log.error("User not found for mobile number:  {}" , auth.getMobileNumber());
	            return null;
	        }
	    } catch (Exception e) {
	        log.error("Exception: {}" , e.getMessage());
	        return null;
	    }
	}


	
	


}
