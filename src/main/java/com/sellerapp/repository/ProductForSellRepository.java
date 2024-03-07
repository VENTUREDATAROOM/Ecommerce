package com.sellerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.ProductForSell;

public interface ProductForSellRepository extends JpaRepository<ProductForSell, Long> {

	List<ProductForSell> findByProductName(String productName);

	ProductForSell findByProductFinalCode(String productFinalCode);

	List<ProductForSell> findByProductMasterCode(String productMasterCode);

	ProductForSell findByProductMasterSubCode(String productMasterSubcode);

}
