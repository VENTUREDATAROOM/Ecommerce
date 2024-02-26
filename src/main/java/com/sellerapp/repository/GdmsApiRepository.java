package com.sellerapp.repository;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sellerapp.entity.GdmsApiUsers;
public interface GdmsApiRepository extends JpaRepository<GdmsApiUsers, String>
{

	Optional<GdmsApiUsers> findByEmail(String email);
	GdmsApiUsers findByUserCode(String userCode);
	
	Optional<GdmsApiUsers> findByMobileNumber(String mobileNumber);
	Optional<GdmsApiUsers> findByMobileNumberAndOtp(String mobileNumber, String otp);
	
	 
	@Modifying
	@Transactional
	@Query(value = "update api_user set otp =:otp where mobile_number=:mobileNumber  ",nativeQuery = true)
	int updatetOtp(@Param ("mobileNumber")String mobileNumber, @Param ("otp")String otp);

}