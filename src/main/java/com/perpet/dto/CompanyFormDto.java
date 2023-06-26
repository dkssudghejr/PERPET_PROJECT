package com.perpet.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CompanyFormDto {
	
	@NotBlank(message = "이메일를 입력해주세요.")
	private String cpemail;
	
	@NotEmpty(message = "패스워드를 입력해주세요.")
	@Length(min=8, max=15, message="비밀번호는 8~15글자로 입력하세요.")
	private String cp_pw;
	
	@NotEmpty(message = "업체명을 입력해주세요.")
	private String cp_name;
	
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String cp_tel;
	
	@NotEmpty(message = "주소를 입력해주세요.")
	private String cp_addr;
	
	@NotEmpty(message = "사업자 등록 번호는 필수입니다.")
	private String cp_registnum;
}
