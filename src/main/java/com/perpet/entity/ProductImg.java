package com.perpet.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product_img")
@Getter @Setter
public class ProductImg extends BaseEntity{
	
	@Id
	@Column(name="product_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Column(name="pi_imgName")
	private String imgName; //이미지 파일명
	
	@Column(name="pi_oriImgName")
	private String oriImgName; //원본 이미지 파일명
	
	@Column(name="pi_imgUrl")
	private String imgUrl; //이미지 조회할 경로
	
	@Column(name="pi_repImgYn")
	private String repImgYn; //대표이미지 여부
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	public void updateProductImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
	
}
