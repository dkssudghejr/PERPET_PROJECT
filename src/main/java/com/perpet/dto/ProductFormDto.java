package com.perpet.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.perpet.contstant.ProductSellStatus;
import com.perpet.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductFormDto {
	
	private Long id;
	
	@NotNull(message="카테고리는 필수입니다.")
	private Integer cate;
	
	@NotBlank(message="온라인 구매 가능 여부는 필수입니다.")
	private String onlineBuy;
	
	@NotBlank(message="상품명은 필수입니다.")
	private String name;
	
	@NotNull(message="가격은 필수입니다.")
	private Integer price;
	
	@NotBlank(message="제조사는 필수입니다.")
	private String madeBy;
	
	@NotNull(message="재고는 필수입니다.")
	private Integer stockNumber;
	
	@NotBlank(message="대표성분은 필수입니다.")
	private String ingredient;
	
	@NotBlank(message="상세 설명은 필수입니다.")
	private String detail;
	
	private ProductSellStatus productSellStatus; //판매상태
	
	//상품을 저장한 후 변경할 때 상품이미지 정보를 저장하는 리스트
	private List<ProductImgDto> productImgDtoList = new ArrayList<>();
	
	//상품의 이미지 아이디를 저장
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Product createProduct() {
		return modelMapper.map(this, Product.class);
	}
	
	public static ProductFormDto of(Product product) {
		return modelMapper.map(product, ProductFormDto.class);
	}
}
