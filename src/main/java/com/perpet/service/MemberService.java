package com.perpet.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpet.entity.Member;
import com.perpet.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		
		return memberRepository.save(member);
	}
	
	//중복된 이메일이 있으면 예외를 발생시킴
		private void validateDuplicateMember(Member member) {
			Optional<Member> findMember = memberRepository.findByMemail(member.getMemail());
			if(findMember != null) { //이미 이메일주소가 존재하면
				
				//IllegalStateException : 현재 객체 상태가 예상과 맞지 않을 때 사용
				throw new IllegalStateException("이미 가입된 회원입니다.");
			}
		}
		
	
	//로그인 인증 처리
	@Override
	public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException{
		Optional<Member> member = memberRepository.findByMemail(memail);
		
		if(member == null) {
			throw new UsernameNotFoundException(memail);
		}
		
		return User.builder().username(member.get().getMemail())
							 .password(member.get().getM_pw())
							 .roles(member.get().getRole().toString())
							 .build();
	}
}
