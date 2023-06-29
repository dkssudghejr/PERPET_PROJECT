package com.perpet.dto;

import org.modelmapper.ModelMapper;

import com.perpet.entity.ProductImg;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductImgDto {
	
	private Long id;
	
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 파일명
	
	private String imgUrl; //이미지 조회할 경로
	
	private String repImgYn; //대표이미지 여부
	
	private static ModelMapper modelMapper = new ModelMapper(); //modelMapper라이브러리 사용
	
	//ModelMapper객체를 생성하기 위한 메서드
	public static ProductImgDto of(ProductImg productImg) {
		return modelMapper.map(productImg, ProductImgDto.class);
	}
}
