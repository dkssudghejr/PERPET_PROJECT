package com.perpet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.ProductImg;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long>{
	List<ProductImg> findByProductIdOrderByIdAsc(Long productId);
}
