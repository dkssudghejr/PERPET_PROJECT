package com.perpet.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perpet.dto.MemberFormDto;
import com.perpet.entity.Member;
import com.perpet.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	//회원가입을 클릭하면
	@GetMapping("/new")
	public String userForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/regChoice";
	}
	
	@GetMapping("/newm")
	public String memberTerms(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/termsForm";
	}
	
	@GetMapping("/newc")
	public String corpTerms(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/termsForm";
	}
	@GetMapping("/newm/join")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/joinForm";
	}
	
	@GetMapping("/newc/join")
	public String corpForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/corpJoinForm";
	}
	
	//가입을 하면 /로 이동
	@PostMapping("/newm")
	public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/joinForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/joinForm";
		}
		
		return "member/loginForm";
	}
	
	@PostMapping("/newc")
	public String newCorp(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "member/corpJoinForm";
		}
		
		try {
			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/corpJoinForm";
		}
		
		return "member/loginForm";
	}
	
	@GetMapping("/login")
	public String loginMember() {
		return "/member/loginForm";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요.");
		return "/member/loginForm";
	}
}
