package com.perpet.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MemberFormDto {
	
	@NotBlank(message = "이메일를 입력해주세요.")
	private String email;
	
	@NotEmpty(message = "패스워드를 입력해주세요.")
	@Length(min=8, max=15, message="비밀번호는 8~15글자로 입력하세요.")
	private String pw;
	
	@NotEmpty(message = "이름을 입력해주세요.")
	private String name;
	
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String tel;

	@NotEmpty(message = "주소를 입력해주세요.")
	private String addr;
	
	/*
	 * private LocalDateTime m_rdate;
	 * 
	 * private String m_terms;
	 * 
	 * private String m_drop;
	 */
	
	
}
