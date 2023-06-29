package com.perpet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.perpet.dto.ProductFormDto;
import com.perpet.dto.ProductImgDto;
import com.perpet.entity.Product;
import com.perpet.entity.ProductImg;
import com.perpet.repository.ProductImgRepository;
import com.perpet.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductImgService productImgService;
	private final ProductImgRepository productImgRepository;
	
	public Long saveProduct(ProductFormDto productFormDto, List<MultipartFile> productImgFileList) throws Exception{
		
		//상품 등록
		Product product = productFormDto.createProduct();
		//상품을 저장
		productRepository.save(product);
		//이미지 등록
		for(int i=0; i<productImgFileList.size(); i++) {
			ProductImg productImg = new ProductImg();
			productImg.setProduct(product);
			
			//대표이미지
			if(i==0) {
				productImg.setRepImgYn("Y");
			}else {
				productImg.setRepImgYn("N");
			}
			
			//이미지 정보 저장
			productImgService.saveProductImg(productImt, productImgFileList.get(i));
		}
		
		return product.getId();
	}
	
	@Transactional(readOnly=true)
	public ProductFormDto getProductDtl(Long productId) {
		//상품 이미지 조회
		List<ProductImg> productImgList = productImgRepository.findByProductIdOrderByIdAsc(productId);
		List<ProductImgDto> productImgDtoList = new ArrayList<>();
		
		for(ProductImg productImg : productImgList) {
			ProductImgDto productImgDto = ProductImgDto.of(productImg);
			productImgDtoList.add(productImgDto);
		}
		
		//상품의 아이디를 통해 상품엔티티 검색
		Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
		ProductFormDto productFormDto = ProductFormDto.of(product);
		productFormDto.setProductImgDtoList(productImgDtoList);
		
		return productFormDto;
	}
	
	//상품 수정
	public Long updateProduct(ProductFormDto productFormDto, List<MultipartFile> productImgFileList) throws Exception{
		Product product = productRepository.findById(productFormDto.getId()).orElseThrow(EntityNotFoundException::new);
		
		product.updateProduct(productFormDto);
	}
}
