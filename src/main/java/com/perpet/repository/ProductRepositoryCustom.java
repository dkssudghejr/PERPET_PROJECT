package com.perpet.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.perpet.entity.Product;

public interface ProductRepositoryCustom {
	
	//상품 조회 조건을 담고 있는 itemSearchDto
	Page<Product> getCompanyProductPage(ProductSearchDto productSearchDto, Pageable pageable);
}
