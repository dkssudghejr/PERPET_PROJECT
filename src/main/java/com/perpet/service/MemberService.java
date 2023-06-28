package com.perpet.service;

import java.util.List;
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
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {

		return memberRepository.save(member);
	}

	// 로그인 인증 처리
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> member = memberRepository.findByEmail(email);

		if (member == null) {
			throw new UsernameNotFoundException(email);
		}

		return User.builder().username(member.get().getEmail()).password(member.get().getPw())
				.roles(member.get().getRole().toString()).build();
	}
	
	//이메일 중복 검사
	public boolean isEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }
	
	//사업자 등록 번호 중복 검사
	public boolean isRegistnumDuplicate(String registnum) {
		return memberRepository.existsByRegistnum(registnum);
	}
	
	//승인되지 않은 업체 목록
	public List<Member> approvalNCompany(String approval){
		return memberRepository.findByApproval(approval);
	}
	
}
