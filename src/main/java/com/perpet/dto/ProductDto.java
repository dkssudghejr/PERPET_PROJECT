package com.perpet.dto;

import java.time.LocalDateTime;

import com.perpet.contstant.ProductSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ProductDto {
	
	private Long id; //상품 아이디
	
	private int cate; //상품 카테고리
	
	private String name; //상품명
	
	private int price; //가격
	
	private String madeBy; //제조사
	
	private int stockNumber; //재고수량
	
	private String detail; //상세 설명
	
	private String ingredient; //대표성분
	
	private String onlineBuy; //온라인 구매 가능 여부
	
	private int wish; //좋아요 수
	
	private String approval; //관리자 승인
	
	private ProductSellStatus productSellStatus; //판매 상태
	
	private LocalDateTime regTime; // 등록시간

	private LocalDateTime updateTime; // 변경시간
}
