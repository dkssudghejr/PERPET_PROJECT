package com.perpet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.perpet.contstant.ProductSellStatus;
import com.perpet.dto.ProductFormDto;
import com.perpet.exception.OutOfStockException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="product")
@Getter @Setter
@ToString
@DynamicInsert
public class Product extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "product_id")
	private Long id;
	
	@Column(name =  "p_cate", nullable = false)
	private int cate;
	
	@Column(name =  "p_name", nullable = false)
	private String name;
	
	@Column(name =  "p_price", nullable = false)
	private int price;
	
	@Column(name =  "p_madeBy", nullable = false)
	private String madeBy;
	
	@Column(name =  "p_stockNumber")
	private int stockNumber;
	
	@Column(name =  "p_detail", nullable = false)
	private String detail;
	
	//대표성분
	@Column(name =  "p_ingredient", nullable = false)
	private String ingredient;
	
	//온라인 구매 가능 여부
	@Column(name =  "p_onlineBuy", nullable = false)
	private String onlineBuy;
	
	@Column(name =  "p_wish")
	@ColumnDefault("0")
	private int wish;
	
	//관리자 승인
	@Column(name =  "p_approval")
	@ColumnDefault("'N'")
	private String approval;
	
	@Enumerated(EnumType.STRING)
	private ProductSellStatus productSellStatus; 	
	
	public void updateProduct(ProductFormDto productFormDto) {
		this.cate = productFormDto.getCate();
		this.onlineBuy = productFormDto.getOnlineBuy();
		this.name = productFormDto.getName();
		this.price = productFormDto.getPrice();
		this.madeBy = productFormDto.getMadeBy();
		this.stockNumber = productFormDto.getStockNumber();
		this.ingredient = productFormDto.getIngredient();
		this.detail = productFormDto.getDetail();
		this.productSellStatus = productFormDto.getProductSellStatus();
	}
	
	public void removeStock(int stockNumber) {
		//상품의 재고 수량에서 주문 후 남은 재고 수량을 계산
		int restStock = this.stockNumber - stockNumber;
		
		if(restStock < 0) {
			//상품의 재고가 주문 수량보다 적으면 재고 부족으로 예외 발생
			throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber + ")");
		} else if (restStock == 0) {
			productSellStatus = ProductSellStatus.SOLD_OUT;
		}
		//주문 후 남은 재고 수량을 상픔의 현재 수량으로 설정
		this.stockNumber = restStock;
	}
	
	//상품의 재고를 증가 (주문이 취소 됐을 때)
	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
	}
}
