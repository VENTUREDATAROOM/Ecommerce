package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.AvailableProductImage;

public interface AvailableProductImageRepository extends JpaRepository<AvailableProductImage, Long> {

	AvailableProductImage findByOrderCode(String availableCode);

}
