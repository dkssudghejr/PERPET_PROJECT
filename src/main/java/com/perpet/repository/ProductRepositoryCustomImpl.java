package com.perpet.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.perpet.contstant.ProductSellStatus;
import com.perpet.dto.ProductSearchDto;
import com.perpet.entity.Product;
import com.perpet.entity.QProduct;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

	//동적으로 쿼리를 생성하기 위해서 JpaQueryFactory클래스를 사용함
	private JPAQueryFactory queryFactory;
	
	//JPAQueryFactory의 생성자로 EntityManager를 초기화
	public ProductRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	//상품 판매 상태 조건이 null이면 (전체)null 리턴
	//결과값이 null이면 해당 조건은 무시됨
	//판매 상태 조건이 null이 아니면 판매중 또는 품절상태에 해당하는 상품을 조회
	private BooleanExpression searchSellStatusEq (ProductSellStatus searchSellStatus) {
		return searchSellStatus == null ? null : QProduct.product.productSellStatus.eq(searchSellStatus);
	}
	
	//등록된 시간으로 상품 조회
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		
		if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if(StringUtils.equals("1d", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if(StringUtils.equals("1w", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if(StringUtils.equals("1m", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if(StringUtils.equals("6m", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		}
		//해당 시간 이후로 등록된 상품만 조회하도록 함
		return QProduct.product.regTime.after(dateTime);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("productName", searchBy)) {
			return QProduct.product.name.like("%" + searchQuery + "%");
		} else if (StringUtils.equals("createBy", searchQuery)) {
			return QProduct.product.createBy.like("%" + searchQuery + "%");
		}
	
		return null;
	}
	
	@Override
	public Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable) {
		//쿼리를 실행해서 조회된 항목을 가져옴
		List<Product> content = queryFactory.selectFrom(QProduct.product)
					.where(regDtsAfter(productSearchDto.getSearchDateType()),
							searchSellStatusEq(productSearchDto.getSearchSellStatus()),
							searchByLike(productSearchDto.getSearchBy(), productSearchDto.getSearchQuery()))
					.orderBy(QProduct.product.id.desc())
					.offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
		
		//전체 항목수 조회
		long total = queryFactory.select(Wildcard.count).from(QProduct.product)
						.where(regDtsAfter(productSearchDto.getSearchDateType()),
								searchSellStatusEq(productSearchDto.getSearchSellStatus()),
								searchByLike(productSearchDto.getSearchBy(), productSearchDto.getSearchQuery()))
						.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}

}
