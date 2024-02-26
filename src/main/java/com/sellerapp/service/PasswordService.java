package com.sellerapp.service;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.entity.OtpEntity;
import com.sellerapp.model.ForgetPasswordDTO;
import com.sellerapp.model.ChangePasswordDTO;
import com.sellerapp.model.VerifyOtpDTO;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.repository.UserRepository;

@Service
public class PasswordService {


	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordService.class);

	@Autowired
	private GdmsApiRepository gdmsRepository;

	

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private UserRepository userRepository;

	public String changePassword(ChangePasswordDTO changePasswordDTO)
	{
		try
		{
			/*if(!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
				return "pass word did not match";
			}*/


			/*Optional<GdmsApiUsers> us=gdmsRepository.findByMobileNumber(resetPasswordDTO.getMobileNumber());
			if(!us.isPresent())
			{
				return "User not found";
			}else {
				GdmsApiUsers user=us.get();
				String originalPassword=resetPasswordDTO.getOriginalPassword();
				if(!bcryptEncoder.matches(originalPassword, user.getPassword()))
				{
					return "old password does not match";
				}
				String newHashPassword=bcryptEncoder.encode(resetPasswordDTO.getNewPassword());


				user.setPassword(newHashPassword);
				gdmsRepository.save(user);
				System.out.println("Password resent successfully" +resetPasswordDTO.getMobileNumber());
				log.info("password success changed for the user .{}",resetPasswordDTO.getMobileNumber());
				return "Success";

			}*/
			String mobileNumber=changePasswordDTO.getMobileNumber();
			String oldPassword=changePasswordDTO.getOldPassword();
			String newPassword=changePasswordDTO.getNewPassword();
			Optional<GdmsApiUsers> us=gdmsRepository.findByMobileNumber(mobileNumber);
			GdmsApiUsers user=us.get();
			if(user==null)
			{
				return "User was not found";
			}
			
			if(!bcryptEncoder.matches(oldPassword, user.getPassword()))
			{
				return "Incorrect Old Password ";
			}
			String newHashPassword=bcryptEncoder.encode(newPassword);
			user.setPassword(newHashPassword);
			gdmsRepository.save(user);
			System.out.println("Password Update successfully" +mobileNumber);
			log.info("Password success changed for the user .{}{}",mobileNumber);
			return "Success";




		} catch(Exception e) {
			log.error("There is error for reset the password"+ e.getMessage());
			return "Error";
		}
	}
	public String otpSignin(ForgetPasswordDTO forgetPassswordDTO)
	{
		try
		{
			String otp=generateRandomOtp();
			OtpEntity oe=new OtpEntity();
			if(oe!=null)
			{
				
				oe.setUsername(forgetPassswordDTO.getUsername());
				//oe.setPassword(bcryptEncoder.encode(forgetpassswordDto.getPassword()));
				oe.setOtp(otp);
				LocalDateTime currentDateTime=LocalDateTime.now();
				LocalDateTime otpSendStringFormatted = currentDateTime.plusMinutes(1);


				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String otpSendString= otpSendStringFormatted.format(formatter);

				oe.setOtpSend(otpSendString);
				LocalDateTime currentdateTime=LocalDateTime.now();
				LocalDateTime otpExpiryStringFormatted = currentdateTime.plusMinutes(1);


				DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String otpExpiryString= otpExpiryStringFormatted.format(formater);

				oe.setOtpExpiry(otpExpiryString);
				oe=userRepository.saveAndFlush(oe);
				//sendOtpByEmail(email,otp);
				//sendAttachByEmail(email,otp);
				//sendVerificationEmail(email);
				//sendPasswordOtp(username,otp);
			}
			log.info("Otp sign in " +forgetPassswordDTO.getUsername());
			return "Success";


		}  catch(Exception e)
		{
			e.printStackTrace();
			log.error("Error in otpsign: " + e.getMessage());
			return "Error";
		}
	}
	public String generateRandomOtp() {
		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000);
		return String.valueOf(otpValue);
	}
	public String verifyOtp(VerifyOtpDTO verifyOtpDTO)
	{
		try {

			String username = verifyOtpDTO.getUsername();
			String otp = verifyOtpDTO.getOtp();

			Optional<OtpEntity> otpOptional = userRepository.findByUsernameAndOtp(username, otp);
			if (otpOptional.isPresent()) {
				OtpEntity oe = otpOptional.get();
				if (otp.equals(oe.getOtp()) && oe.getOtpExpiry() != null) {
					String otpExpiry = oe.getOtpExpiry();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime otpExpiryDateTime = LocalDateTime.parse(otpExpiry, formatter);
					LocalDateTime currentDateTime = LocalDateTime.now();

					if (otpExpiryDateTime.isAfter(currentDateTime)) {
						log.info("OTP verification successful for user: {}", username);
						return "Success";
					} else {
						log.info("OTP has expired for user: {}", username);
						return "Invalid OTP: OTP has expired";
					}
				} else {
					log.info("Invalid OTP for user: {}", username);
					return "Invalid OTP: OTP mismatch";
				}
			} else {
				log.info("User not found: {}", username);
				return "User not found";
			}
		} catch (Exception e) {
			log.error("Error in OTP verification: {}", e.getMessage());
			return "Error";
		}
	}
}













