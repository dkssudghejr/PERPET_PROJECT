package com.perpet.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//현재 사용자의 정보를 제공하는 인터페이스
public class AuditorAwareImpl implements AuditorAware<String>{

	//현재 인증 정보를 가져옴
	@Override
	public Optional<String> getCurrentAuditor() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String userId = "";
		if(authentication != null) 
			userId = authentication.getName();
		
		return Optional.of(userId);
	}

}
