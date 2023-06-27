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
import com.perpet.dto.CompanyFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="company")
@Getter
@Setter
@ToString
@DynamicInsert
public class Company {

	@Id
	@Column(name="cp_email")
	private String email;
	
	@Column(name="cp_pw")
	private String pw;
	
	@Column(name="cp_name")
	private String name;
	
	@Column(unique = true, name="cp_tel")
	private String tel;
	
	@Column(name="cp_addr")
	private String addr;
	
	//사업자 등록번호
	@Column(unique = true, name="cp_registnum")
	private String registnum;

	
	@Column(name="cp_rdate")
	@ColumnDefault("SYSDATE")
	private LocalDateTime rdate;
	
	
	@Column(name="cp_terms")
	@ColumnDefault("'Y'")
	private String terms;
	
	
	@Column(name="cp_drop")
	@ColumnDefault("'N'")
	private String drop;
	
	//관리자 승인
	@ColumnDefault("'N'")
	private String approval;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Company createCompany(CompanyFormDto companyFormDto, PasswordEncoder passwordEncoder) {
		Company company = new Company();
		company.setEmail(companyFormDto.getEmail());
		String password = passwordEncoder.encode(companyFormDto.getPw());
		company.setPw(password);
		company.setName(companyFormDto.getName());
		company.setTel(companyFormDto.getTel());
		company.setAddr(companyFormDto.getAddr());
		company.setRegistnum(companyFormDto.getRegistnum());
		
		company.setRole(Role.COMPANY);
		return company;
	}
}
