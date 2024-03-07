package com.sellerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.AvailableProductForSell;

public interface AvailableProductForSellRepository extends JpaRepository<AvailableProductForSell, Long> {

	List<AvailableProductForSell> findByProductMasterCode(String productMasterCode);

}
