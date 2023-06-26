package com.perpet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perpet.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByMemail(String memail);
}
