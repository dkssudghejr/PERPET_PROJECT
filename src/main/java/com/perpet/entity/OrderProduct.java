package com.perpet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="order_product")
@Getter @Setter
@ToString
public class OrderProduct extends BaseEntity{
	//주문상품 엔티티
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_product_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	@Column(name="op_orderPrice")
	private int orderPrice;
	
	@Column(name="op_count")
	private int count;
	
}
