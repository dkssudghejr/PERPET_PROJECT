package com.perpet.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainProductDto {
	private Long id;
	
	private String name;
	
	private String detail;
	
	private String imgUrl;
	
	private Integer price;
	
	private String madeBy;
	
	private Integer wish;
	
	@QueryProjection
	public MainProductDto(Long id, String name, String detail, String imgUrl, Integer price, String madeBy, Integer wish) {
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.imgUrl = imgUrl;
		this.price = price;
		this.madeBy = madeBy;
		this.wish = wish;
	}
}
