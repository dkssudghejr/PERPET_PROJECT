package com.perpet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.perpet.dto.ProductSearchDto;
import com.perpet.entity.Product;

public interface ProductRepositoryCustom {
	
	//상품 조회 조건을 담고 있는 productSearchDto
	Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);
	
	/*
	 * Page<MainProductDto> getMainProductPage(ProductSearchDto iteSearchDto,
	 * Pageable pageable);
	 */
}
