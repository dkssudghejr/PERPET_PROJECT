package com.perpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
