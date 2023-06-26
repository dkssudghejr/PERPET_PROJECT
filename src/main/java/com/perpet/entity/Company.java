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
	private String cpemail;
	
	private String cp_pw;
	
	private String cp_name;
	
	@Column(unique = true)
	private String cp_tel;
	
	private String cp_addr;
	
	//사업자 등록번호
	@Column(unique = true)
	private String cp_registnum;
	
	@ColumnDefault("SYSDATE")
	private LocalDateTime cp_rdate;
	
	@ColumnDefault("'Y'")
	private String cp_terms;
	
	@ColumnDefault("'N'")
	private String cp_drop;
	
	//관리자 승인
	@ColumnDefault("'N'")
	private String cp_approval;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Company createCompany(CompanyFormDto companyFormDto, PasswordEncoder passwordEncoder) {
		Company company = new Company();
		company.setCpemail(companyFormDto.getCpemail());
		String password = passwordEncoder.encode(companyFormDto.getCp_pw());
		company.setCp_pw(password);
		company.setCp_name(companyFormDto.getCp_name());
		company.setCp_tel(companyFormDto.getCp_tel());
		company.setCp_addr(companyFormDto.getCp_addr());
		company.setCp_registnum(companyFormDto.getCp_registnum());
		
		company.setRole(Role.COMPANY);
		return company;
	}
}
