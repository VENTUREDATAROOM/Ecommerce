package com.sellerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellerapp.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
