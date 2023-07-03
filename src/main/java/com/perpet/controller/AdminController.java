package com.perpet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perpet.contstant.Role;
import com.perpet.entity.Member;
import com.perpet.entity.Product;
import com.perpet.repository.MemberRepository;
import com.perpet.service.MemberService;
import com.perpet.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final ProductService productService;

	@GetMapping
	public String admin(Model model) {
		String approval = "N";
 
		List<Member> companyList = memberService.approvalNCompany(approval);
		List<Product> productList = productService.approvalNProduct(approval);
		model.addAttribute("productList", productList);
		model.addAttribute("companyList", companyList);

		return "adminMain";
	}

	@PostMapping("/approve/{email}")
	public ResponseEntity<String> approveCompany(@PathVariable("email") String email) {
		String approval = "Y";
		Role role = Role.COMPANY;
		memberRepository.updateApproval(approval, role, email);
		return ResponseEntity.ok("승인 완료");
	}
	
	@PostMapping("/dltCompany/{email}")
	public ResponseEntity<String> deleteCompany(@PathVariable("email") String email) {
		memberRepository.deleteByEmail(email);
		return ResponseEntity.ok("거절 완료");
	}
}
