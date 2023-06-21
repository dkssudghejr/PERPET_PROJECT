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
@Table(name="member")
@Getter
@Setter
@ToString
@DynamicInsert
public class Member {
	
	@Id
	@Column(name="m_id")
	private String m_id;
	
	private String m_pw;
	
	private String m_name;
	
	@Column(unique = true)
	private String m_tel;
	
	private String m_addr;
	
	@ColumnDefault("SYSDATE")
	private LocalDateTime m_rdate;
	
	@ColumnDefault("'Y'")
	private String m_terms;
	
	@ColumnDefault("'N'")
	private String m_drop;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//Member엔티티 생성
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setM_id(memberFormDto.getM_id());
		member.setM_pw(memberFormDto.getM_pw());
		member.setM_name(memberFormDto.getM_name());
		member.setM_tel(memberFormDto.getM_tel());
		member.setM_addr(memberFormDto.getM_addr());
		/*
		 * member.setM_rdate(memberFormDto.getM_rdate());
		 * member.setM_terms(memberFormDto.getM_terms());
		 * member.setM_drop(memberFormDto.getM_drop());
		 */
		
		String pw = passwordEncoder.encode(memberFormDto.getM_pw());
		member.setM_pw(pw);
		member.setRole(Role.USER);
		return member;
	}
}
