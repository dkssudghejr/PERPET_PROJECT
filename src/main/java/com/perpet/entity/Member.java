package com.perpet.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.perpet.contstant.Role;
import com.perpet.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@DynamicInsert
public class Member {

	@Id
	@Column(name = "m_email")
	private String email;

	@Column(name = "m_pw")
	private String pw;

	@Column(name = "m_name")
	private String name;

	@Column(unique = true, name = "m_tel")
	private String tel;

	@Column(name = "m_addr")
	private String addr;

	// 사업자 등록번호
	@Column(name = "m_registnum")
	private String registnum;

	@Column(name = "m_rdate")
	@ColumnDefault("SYSDATE")
	private LocalDateTime rdate;

	//약관 동의 여부
	@Column(name = "m_terms")
	@ColumnDefault("'Y'")
	private String terms;

	//탈퇴 여부
	@Column(name = "m_drop")
	@ColumnDefault("'N'")
	private String drop;

	// 관리자 승인
	@Column(name = "m_approval")	
	@ColumnDefault("'Y'")
	private String approval;

	//회원 유형
	@Enumerated(EnumType.STRING)
	private Role role;

	// Member엔티티 생성
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberFormDto.getEmail());
		String password = passwordEncoder.encode(memberFormDto.getPw());
		member.setPw(password);
		member.setName(memberFormDto.getName());
		member.setTel(memberFormDto.getTel());
		member.setAddr(memberFormDto.getAddr());
		member.setRegistnum("NULL");

		member.setRole(Role.USER);
		member.setApproval("Y");
		return member;
	}
	public static Member createCompany(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberFormDto.getEmail());
		String password = passwordEncoder.encode(memberFormDto.getPw());
		member.setPw(password);
		member.setName(memberFormDto.getName());
		member.setTel(memberFormDto.getTel());
		member.setAddr(memberFormDto.getAddr());
		member.setRegistnum(memberFormDto.getRegistnum());
		
		member.setRole(Role.USER);		
		member.setApproval("N");
		return member;
	}
}
