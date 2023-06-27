package com.perpet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	//이메일로 찾기
	Optional<Member> findByEmail(String email);
	
	//이메일 중복 여부
	boolean existsByEmail(String email);
	
	//사업자 등록 번호 중복 여부
	boolean existsByRegistnum(String registnum);
}
