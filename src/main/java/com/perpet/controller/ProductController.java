package com.perpet.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.perpet.dto.MemberFormDto;
import com.perpet.dto.ProductFormDto;
import com.perpet.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	//상품 등록 페이지 이동
	@GetMapping("/company/product/new")
	public String productForm(Model model) {
		model.addAttribute("productFormDto", new ProductFormDto());
		return "/product/productForm";
	}
	
	//상품 등록하기
	//@Valid로 유효성 검사를 하고 BindingResult에 저장
	//productImgFile을 요청했다면, productImgFileList로 저장하여 처리
	@PostMapping("/company/product/new")
	public String productNew(@Valid ProductFormDto productFormDto, BindingResult bindingResult, MemberFormDto memberFormDto,
						Model model, @RequestParam("productImgFile") List<MultipartFile> productImgFileList) {
		
		//유효성 검사를 통과하지 못하면 상품 등록 페이지로 이동
		if(bindingResult.hasErrors()) {
			return "product/productForm";
		}
		
		//대표이미지가 없다면
		if(productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
			model.addAttribute("errorMessage", "대표이미지는 필수입니다.");
			
			return "product/productForm";
		}
		
		try {
			productService.saveProduct(productFormDto, productImgFileList);
		} catch(Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "product/productForm";
		}
		
		return "redirect:/company";
		
	}
	
	//상품 정보 불러오기
	@GetMapping("/company/product/{productId}")
	public String productDtl(@PathVariable("productId") Long productId, Model model) {
		try {
			//조회할 상품 데이터 가져옴
			ProductFormDto productFormDto = productService.getProductDtl(productId);
			
			//가져온 productFormDto를 model에 추가함
			model.addAttribute("productFormDto", productFormDto);
		} catch(EntityNotFoundException e) {
			//상품이 존재하지 않으면 에러메세지를 model에 추가하고
			//새로운 ProductFormDto 객체도 model에 추가
			model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
			model.addAttribute("productFormDto", new ProductFormDto());
			
			return "product/productForm";
		}
		
		return "product/productForm";
	}
	
	//상품 수정을 클릭하면
	@PostMapping("/company/product/{productId}")
	public String productUpdate(@Valid ProductFormDto productFormDto, BindingResult bindingResult, 
							@RequestParam("productImgFile") List<MultipartFile> productImgFileList, Model model){
		//유효성 검사를 통과하지 못하면 상품등록 페이지로 이동
		if(bindingResult.hasErrors()) {
			return "product/productForm";
		}
		
		//대표이미지가 없다면
		if(productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
			model.addAttribute("errorMessage", "상품이미지는 필수 입니다.");
			
			return "product/productForm";
		}
		
		try {
			productService.saveProduct(productFormDto, productImgFileList);
		}catch(Exception e) {
			model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
			return "product/productForm";
		}
		return "redirect:/company";
	}
	
}
