package com.perpet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	boolean existsByRegistnum(String registnum);
}
