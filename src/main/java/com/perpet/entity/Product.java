package com.perpet.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.perpet.contstant.ProductSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="product")
@Getter @Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "p_id")
	private Long id;
	
	@Column(name =  "p_cate", nullable = false)
	private int cate;
	
	@Column(name =  "p_name", nullable = false)
	private String name;
	
	@Column(name =  "p_made", nullable = false)
	private String made;
	
	@Column(name =  "p_price", nullable = false)
	private int price;
	
	@Column(name =  "p_stockNumber")
	private int stockNumber;
	
	@Column(name =  "p_detail", nullable = false)
	private String detail;
	
	//대표성분
	@Column(name =  "p_ingredient", nullable = false)
	private String ingredient;
	
	@Column(name =  "p_rdate")
	@ColumnDefault("SYSDATE")
	private LocalDateTime rdate;
	
	//온라인 구매 가능 여부
	@Column(name =  "p_onlineBuy", nullable = false)
	private String onlineBuy;
	
	@Column(name =  "p_wish")
	@ColumnDefault("0")
	private int wish;
	
	//관리자 승인
	@Column(name =  "p_approval", nullable = false)
	private String approval;
	
	@Enumerated(EnumType.STRING)
	private ProductSellStatus productSellStatus; 
}
