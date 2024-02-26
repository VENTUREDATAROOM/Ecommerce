package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.ProductImageEntity;

public interface ProductImageRepo extends JpaRepository<ProductImageEntity,Long> 
{
  ProductImageEntity findByImageId(Long imageId);
}
