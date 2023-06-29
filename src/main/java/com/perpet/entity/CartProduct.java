package com.perpet.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cart_product")
@Getter @Setter
@ToString
public class CartProduct extends BaseEntity{
	//장바구니 상품 엔티티
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cart_product_id")	
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;

	@Column(name="cp_count")
	private int count;
}
