package com.perpet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.perpet.dto.ProductFormDto;

@Controller
public class ProductController {
	
	@GetMapping("/company/product/new")
	public String productForm() {
		return "/product/productForm";
	}
	
	@PostMapping("/company/product/new")
	public String productNew(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
						Model model, @RequestParam("productImgFile") List<MultipartFile> productImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "product/productForm";
		}
		
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
}
