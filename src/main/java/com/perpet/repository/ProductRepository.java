package com.perpet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.perpet.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>{

	//상품명으로 조회
	List<Product> findByName(String name);
	
	//상품명 또는 상세 설명을 조건
	List<Product> findByNameOrDetail(String name, String detail);
	
	//제조사로 상품을 조회
	@Query("SELECT p FROM Product p WHERE p.madeBy like %:madeBy% ORDER BY p.price DESC")
	List<Product> findByMade(@Param("madeBy")String madeBy);
}
