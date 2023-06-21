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
		return memberRepository.save(member);
	}
	
	//로그인 인증 처리
	@Override
	public UserDetails loadUserByUsername(String m_id) throws UsernameNotFoundException{
		Optional<Member> member = memberRepository.findById(m_id);
		
		if(member == null) {
			throw new UsernameNotFoundException(m_id);
		}
		
		return User.builder().username(member.get().getM_id()).password(member.get().getM_pw()).roles(member.get().getRole().toString()).build();
	}
}
