package com.perpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
