package com.perpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "index";
	}
	
	@GetMapping("/company")
	public String company() {
		return "corpMain";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "adminMain";
	}
	
	
}
