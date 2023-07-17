package com.perpet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.perpet.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> , ProductRepositoryCustom{

	// 상품명으로 조회
	List<Product> findByName(String name);

	// 상품명 또는 상세 설명을 조건
	List<Product> findByNameOrDetail(String name, String detail);

	// 제조사로 상품을 조회
	@Query("SELECT p FROM Product p WHERE p.madeBy like %:madeBy% ORDER BY p.price DESC")
	List<Product> findByMadeBy(@Param("madeBy") String madeBy);

	// 관리자 승인별
	List<Product> findByApproval(String approval);

	// 관리자가 상품 승인
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.approval = :approval WHERE p.id = :id")
	void updateApproval(@Param("approval") String approval, @Param("id") Long id);
	
	//상품 삭제
	@Transactional
	void deleteById(Long id);

}
