package com.perpet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	//상품명으로 조회
	List<Product> findByName(String name);
	
	//상품명 또는 상세 설명을 조건
	List<Product> findByNameOrDetail(String name, String detail);
	
	/*
	 * //가격 내림차순 List<Product> orderByPriceDesc(Integer price); //가격 오름차순
	 * List<Product> orderByPriceAsc(Integer price);
	 */
}
