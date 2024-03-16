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
import com.sellerapp.model.ChangePasswordDTO;
import com.sellerapp.model.ForgetPasswordDTO;
import com.sellerapp.model.ResetPasswordDTO;
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

	public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
		try {

			if (!resetPasswordDTO.getNewPassword().matches(resetPasswordDTO.getRepeatPassword())) {
				return "password did not match";
			}
			GdmsApiUsers p = gdmsRepository.findByUserCode(resetPasswordDTO.getUserCode());
			if (p == null) {
				return "User was not Found";
			} else {
				String newHashPassword = bcryptEncoder.encode(resetPasswordDTO.getNewPassword());
				p.setPassword(newHashPassword);
				gdmsRepository.save(p);
				log.info("Password reset successfully for user : {}", resetPasswordDTO.getUserCode());
				return "Success";
			}
		} catch (Exception e) {
			log.error("Error resetting password: {}", e.getMessage());
			return "Error";
		}

	}

	public String changePassword(ChangePasswordDTO changePasswordDTO) {
		try {

			String mobileNumber = changePasswordDTO.getMobileNumber();
			String oldPassword = changePasswordDTO.getOldPassword();
			String newPassword = changePasswordDTO.getNewPassword();
			Optional<GdmsApiUsers> us = gdmsRepository.findByMobileNumber(mobileNumber);
			GdmsApiUsers user = us.get();
			if (user == null) {
				return "User was not found";
			}

			if (!bcryptEncoder.matches(oldPassword, user.getPassword())) {
				return "Incorrect Old Password ";
			}
			String newHashPassword = bcryptEncoder.encode(newPassword);
			user.setPassword(newHashPassword);
			gdmsRepository.save(user);
			System.out.println("Password Update successfully" + mobileNumber);
			log.info("Password success changed for the user : {}", mobileNumber);
			return "Success";

		} catch (Exception e) {
			log.error("There is error for the password: {}", e.getMessage());
			return "Error";
		}
	}

	public String otpBySign(ForgetPasswordDTO forgetPasswordDTO) {
		try {
			{
				//String userCode = forgetPasswordDTO.getUserCode();
				String username=forgetPasswordDTO.getUsername();
				if (username == null) {
					return "Username is required";
				} else {
                 Optional<GdmsApiUsers> tOptional=gdmsRepository.findByMobileNumber(username);
					//GdmsApiUsers t = gdmsRepository.findByUserCode(userCode);
					if (tOptional.isPresent()) {

						String otp = generateRandomOtp();
                        
						OtpEntity oe = new OtpEntity();
						oe.setUserCode(tOptional.get().getUserCode());
						oe.setUsername(username);
						oe.setOtp(otp);

						LocalDateTime currentDateTime = LocalDateTime.now();
						LocalDateTime otpSendStringFormatted = currentDateTime.plusMinutes(1);

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						String otpSendString = otpSendStringFormatted.format(formatter);
						oe.setOtpSend(otpSendString);

						LocalDateTime currentDateTime2 = LocalDateTime.now();
						LocalDateTime otpExpiryStringFormatted = currentDateTime2.plusMinutes(1);

						String otpExpiryString = otpExpiryStringFormatted.format(formatter);
						oe.setOtpExpiry(otpExpiryString);

						userRepository.save(oe);

						log.info("Otp sign in " + username);
						return otp;
					} else {
						log.error("User not found with userCode: " + username);
						return "User not found";
					}
				}
			}
		} catch (Exception e) {
			log.error("Error in otpsign: ", e.getMessage());
			return "Error";
		}
	}

	public String generateRandomOtp() {
		Random random = new Random();
		int otpValue = 1000 + random.nextInt(9000);
		return String.valueOf(otpValue);
	}

	/*public String verifyOtp(VerifyOtpDTO verifyOtpDTO) {
		try {
			String userCode = verifyOtpDTO.getUserCode();
			String otp = verifyOtpDTO.getOtp();

			if (userCode == null || otp == null) {
				return "Missing required fields: userCode and otp";
			}
           //Optional<GdmsApiUsers> userOptional=gdmsRepository.findByMobileNumber()
			//Optional<GdmsApiUsers> userOptional = Optional.ofNullable(gdmsRepository.findByUserCode(userCode));
			//if (!userOptional.isPresent()) {
				//return "User not found";
			//}
			
			//String actualUserCode=userOptional.get().getUserCode();

			//Optional<OtpEntity> otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp);
			//if (!otpOptional.isPresent()) {
				//return "No OTP found";
			//}
			Optional<OtpEntity> otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp);
	        if (!otpOptional.isPresent()) {
	            // If OTP not found by user code, try finding by email
	            Optional<GdmsApiUsers> userOptional = gdmsRepository.findByEmail(userCode);
	            if (!userOptional.isPresent()) {
	                return "User not found";
	            }
	            userCode = userOptional.get().getUserCode(); // Update user code
	            otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp); // Try finding OTP again
	            if (!otpOptional.isPresent()) {
	                return "No OTP found";
	            }
	        }

			OtpEntity oe = otpOptional.get();

			if (oe.getOtpExpiry() != null) {
				LocalDateTime otpExpiryDateTime = LocalDateTime.parse(oe.getOtpExpiry(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				LocalDateTime currentDateTime = LocalDateTime.now();

				if (otpExpiryDateTime.isAfter(currentDateTime)) {
					return "Success";
				} else {
					return "Invalid OTP: OTP has expired";
				}
			} else {
				return "Invalid OTP: OTP expiry information not found";
			}
		} catch (Exception e) {
			return "Error occurred while verifying OTP";
		}
	}*/
	public String verifyOtp(VerifyOtpDTO verifyOtpDTO) {
	    
	        String userCode = verifyOtpDTO.getUserCode();
	        String otp = verifyOtpDTO.getOtp();

	        if (userCode == null || otp == null) {
	            return "Missing required fields: userCode and otp";
	        }

	        Optional<OtpEntity> otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp);
	        
	        
	        //..............
	        
	        if (otpOptional.isPresent()) {
                return "Success";
            } else {
                return "Invalid OTP: OTP has expired";
            }
	        
	        
	        
	        
	        //.................
	        
	        
	        
	        
//	        if (!otpOptional.isPresent()) {
//	            // If OTP not found by user code, try finding by email
//	            Optional<GdmsApiUsers> userOptional;
//	            if (verifyOtpDTO.getEmail() != null) {
//	                userOptional = gdmsRepository.findByEmail(verifyOtpDTO.getEmail());
//	            } else if (verifyOtpDTO.getUsername() != null) {
//	                userOptional = gdmsRepository.findByMobileNumber(verifyOtpDTO.getUsername());
//	            } else {
//	                return "User not found";
//	            }
//
//	            if (!userOptional.isPresent()) {
//	                return "User not found";
//	            }
//
//	            userCode = userOptional.get().getUserCode(); // Update user code
//	            otpOptional = userRepository.findByUserCodeAndOtp(userCode, otp); // Try finding OTP again
//	            if (!otpOptional.isPresent()) {
//	                return "No OTP found";
//	            }
//	        }
//
//	        OtpEntity oe = otpOptional.get();
//
//	        if (oe.getOtpExpiry() != null) {
//	            LocalDateTime otpExpiryDateTime = LocalDateTime.parse(oe.getOtpExpiry(),
//	                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//	            LocalDateTime currentDateTime = LocalDateTime.now();
//
//	            if (otpExpiryDateTime.isAfter(currentDateTime)) {
//	                return "Success";
//	            } else {
//	                return "Invalid OTP: OTP has expired";
//	            }
//	        } else {
//	            return "Invalid OTP: OTP expiry information not found";
//	        }
//	    } catch (Exception e) {
//	        return "Error occurred while verifying OTP";
//	    }
	}


}