package com.perpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{

}
