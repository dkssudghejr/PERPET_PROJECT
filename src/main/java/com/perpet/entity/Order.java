package com.perpet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.perpet.contstant.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders")
@Getter @Setter
@ToString
public class Order extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="member_email")
	private Member member;
	
	//배송지
	@Column(name="o_addr")
	private String addr;
	
	//배송비
	@Column(name="o_deliveryFee")
	private int deliveryFee;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
}
