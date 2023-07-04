package com.perpet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perpet.entity.Product;
import com.perpet.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
	
	private final ProductService productService;
	
	@GetMapping
	public String company(Model model) {
		String approval = "N";
		
		List<Product> productList = productService.approvalNProduct(approval);
		model.addAttribute("productList", productList);
		
		return "corpMain";
	}
}
