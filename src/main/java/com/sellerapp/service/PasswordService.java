package com.sellerapp.service;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sellerapp.entity.GdmsApiUsers;
import com.sellerapp.entity.OtpEntity;
import com.sellerapp.model.ForgetPasswordDto;
import com.sellerapp.model.ResetPasswordDto;
import com.sellerapp.model.VerifyOtpDto;
import com.sellerapp.repository.GdmsApiRepository;
import com.sellerapp.repository.UserRepository;

@Service
public class PasswordService {


	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PasswordService.class);

	@Autowired
	private GdmsApiRepository gdmsRepository;

	//@Autowired
	//ModelMapper mapper;


	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private UserRepository userRepository;

	public String resetpassword(ResetPasswordDto resetpasswordDto)
	{
		try
		{
			if(!resetpasswordDto.getNewPassword().equals(resetpasswordDto.getConfirmPassword())) {
				return "pass word did not match";
			}


			GdmsApiUsers us=gdmsRepository.findByUserCode(resetpasswordDto.getUserCode());
			if(us==null)
			{
				return "User not found";
			}else {
				String originalHashPassword=bcryptEncoder.encode(resetpasswordDto.getOriginalPassword());
				String newHashPassword=bcryptEncoder.encode(resetpasswordDto.getNewPassword());

				if(originalHashPassword.equals(us.getPassword())) {
					us.setPassword(newHashPassword);
					us=gdmsRepository.saveAndFlush(us);
					System.out.println("Password resent successfully" +resetpasswordDto.getUserCode());
					log.info("password succes changed for the user .{}",resetpasswordDto.getUserCode());
					return "Success";

				}else {
					return "old password does not match";
				}
			}



		} catch(Exception e) {
			log.error("There is error for reset the password"+ e.getMessage());
			return "Error";
		}
	}
	public String otpSignin(ForgetPasswordDto forgetpassswordDto)
	{
		try
		{
			String otp=generateRandomOtp();
			OtpEntity oe=new OtpEntity();
			if(oe!=null)
			{
				oe.setUserCode(forgetpassswordDto.getUserCode());
				oe.setUsername(forgetpassswordDto.getUsername());
				oe.setPassword(bcryptEncoder.encode(forgetpassswordDto.getPassword()));
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
			log.info("Otp sign in " +forgetpassswordDto.getUserCode()+","+forgetpassswordDto.getUsername()+","+forgetpassswordDto.getPassword());
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
	public String verifyOtp(VerifyOtpDto verifyotpDto)
	{
		try {

			String userCode = verifyotpDto.getUserCode();
			String otp = verifyotpDto.getOtp();

			Optional<OtpEntity> otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp);
			if (otpOptional.isPresent()) {
				OtpEntity oe = otpOptional.get();
				if (otp.equals(oe.getOtp()) && oe.getOtpExpiry() != null) {
					String otpExpiry = oe.getOtpExpiry();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime otpExpiryDateTime = LocalDateTime.parse(otpExpiry, formatter);
					LocalDateTime currentDateTime = LocalDateTime.now();

					if (otpExpiryDateTime.isAfter(currentDateTime)) {
						log.info("OTP verification successful for user: {}", userCode);
						return "Success";
					} else {
						log.info("OTP has expired for user: {}", userCode);
						return "Invalid OTP: OTP has expired";
					}
				} else {
					log.info("Invalid OTP for user: {}", userCode);
					return "Invalid OTP: OTP mismatch";
				}
			} else {
				log.info("User not found: {}", userCode);
				return "User not found";
			}
		} catch (Exception e) {
			log.error("Error in OTP verification: {}", e.getMessage());
			return "Error";
		}
	}
}













