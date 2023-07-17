package com.perpet.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perpet.contstant.Role;
import com.perpet.dto.ProductFormDto;
import com.perpet.entity.Member;
import com.perpet.entity.Product;
import com.perpet.repository.MemberRepository;
import com.perpet.repository.ProductRepository;
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
	private final ProductRepository productRepository;
	
	//승인되지 않은 업체
	@GetMapping
	public String admin(Model model) {
		String approval = "N";
 
		List<Member> companyList = memberService.approvalNCompany(approval);
		List<Product> productList = productService.approvalNProduct(approval);
		model.addAttribute("productList", productList);
		model.addAttribute("companyList", companyList);

		return "adminMain";
	}

	//업체 승인
	@PostMapping("/approve/{email}")
	public ResponseEntity<String> approveCompany(@PathVariable("email") String email) {
		String approval = "Y";
		Role role = Role.COMPANY;
		memberRepository.updateApproval(approval, role, email);
		return ResponseEntity.ok("승인 완료");
	}
	//업체 거절
	@PostMapping("/dltCompany/{email}")
	public ResponseEntity<String> deleteCompany(@PathVariable("email") String email) {
		memberRepository.deleteByEmail(email);
		return ResponseEntity.ok("거절 완료");
	}
	
	// 상품 정보
	@GetMapping("/product/{productId}")
	public String productDtl(@PathVariable("productId") Long productId, Model model) {
		try {
			// 조회할 상품 데이터 가져옴
			ProductFormDto productFormDto = productService.getProductDtl(productId);

			// 가져온 productFormDto를 model에 추가함
			model.addAttribute("productFormDto", productFormDto);
		} catch (EntityNotFoundException e) {
			// 상품이 존재하지 않으면 에러메세지를 model에 추가하고
			// 새로운 ProductFormDto 객체도 model에 추가
			model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
			model.addAttribute("productFormDto", new ProductFormDto());

			return "adminMain";
		}

		return "product/adminProductDtl";
	}
	
	//상품 승인
	@PostMapping("/admin/approvalP/{id}")
	public String approveProduct(@PathVariable("id") Long id){
		String approval ="Y";
		productRepository.updateApproval(approval, id);
		return "redirect:/admin";		
	}
	
	//상품 거절
	@PostMapping("/dltCompany/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
		productRepository.deleteById(id);
		return ResponseEntity.ok("거절 완료");
	}
}
