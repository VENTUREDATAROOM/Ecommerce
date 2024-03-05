package com.sellerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.OtpEntity;

public interface UserRepository extends JpaRepository<OtpEntity, Long> {

	Optional<OtpEntity> findByUsernameAndOtp(String username, String otp);

	boolean existsByEmail(String email);
}
