package com.perpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/company/product/new")
	public String productForm() {
		return "/product/productForm";
	}
}
