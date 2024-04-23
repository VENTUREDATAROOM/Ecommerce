package com.sellerapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> 
{
  Optional <ProductEntity> findByProductId(Long productId);
	
}
