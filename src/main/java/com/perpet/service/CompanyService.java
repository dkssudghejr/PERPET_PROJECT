package com.perpet.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpet.entity.Company;
import com.perpet.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService implements UserDetailsService{
private final CompanyRepository companyRepository;
	
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	@Override
	//로그인 인증 처리
	public UserDetails loadUserByUsername(String cpemail) throws UsernameNotFoundException{
		Optional<Company> company = companyRepository.findByCpemail(cpemail);
		
		if(company == null) {
			throw new UsernameNotFoundException(cpemail);
		}
		
		return User.builder().username(company.get().getCpemail()).password(company.get().getCp_pw()).roles(company.get().getRole().toString()).build();
	}
}
