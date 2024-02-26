package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.SaleEntity;
public interface SaleRepository extends JpaRepository<SaleEntity,Long> {
	
	

}
