package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.LoginHistoryEntity;
public interface  LoginHistoryRepo extends JpaRepository<LoginHistoryEntity,Long> 

{

}
