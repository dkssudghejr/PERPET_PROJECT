package com.perpet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {
	
	@GetMapping(value="/ex1")
	public String thymeleafExample1(Model model) {
		model.addAttribute("data", "타임리프 예제 11111111");
		
		return "thymeleafEx/thymeleafEx1";
	}
	
	
	
	@GetMapping(value="/ex5")
	public String thymeleafExample5() {
		return "thymeleafEx/thymeleafEx5";
	}
	
	@GetMapping(value="/ex6")
	//ex5에서 전달한 매개변수와 같은 이름의 String변수를 파라미터로 지정하면
	//자동으로 데이터가 바인딩됨
	public String thymeleafExample6(String param1, String param2, Model model) {
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		return "thymeleafEx/thymeleafEx6";
	}
	
	@GetMapping(value="/ex7")
	public String thymeleafExample7() {
		return "thymeleafEx/thymeleafEx7";
	}
}
