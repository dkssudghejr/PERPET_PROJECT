package com.perpet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.perpet.contstant.Role;
import com.perpet.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>, QuerydslPredicateExecutor<Member>{
	//이메일로 찾기
	Optional<Member> findByEmail(String email);
	
	//이메일 중복 여부
	boolean existsByEmail(String email);
	
	//사업자 등록 번호 중복 여부
	boolean existsByRegistnum(String registnum);
	
	//승인되지 않은 업체
	List<Member> findByApproval(String approval);
	
	//관리자가 업체 승인
	@Modifying
	@Transactional
    @Query("UPDATE Member m SET m.approval = :approval, m.role = :role WHERE m.email = :email")
    void updateApproval(@Param("approval") String approval,@Param("role") Role role, @Param("email") String email);
	
	//업체 삭제
	@Transactional
	void deleteByEmail(String email);
}
