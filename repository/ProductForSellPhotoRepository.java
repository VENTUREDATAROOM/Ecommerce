package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.ProductForSellPhoto;

public interface ProductForSellPhotoRepository extends JpaRepository<ProductForSellPhoto, Long> {

	ProductForSellPhoto findByProductMasterSubCode(String productSubCode);

}
