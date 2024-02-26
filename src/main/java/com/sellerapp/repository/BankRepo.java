package com.sellerapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sellerapp.entity.BankDetailsEntity;
public interface BankRepo  extends JpaRepository<BankDetailsEntity, Long> {
	
   
}
