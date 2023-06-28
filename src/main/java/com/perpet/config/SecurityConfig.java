package com.perpet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //스프링 애플리케이션 컨텍스트에 빈(bean)구성을 제공하는 클래스
@EnableWebSecurity //스프링 시큐리티를 활성하시키는 어노테이션
public class SecurityConfig{
	
	//HttpSecurity객체를 매개변수로 받아 구성하여 반환함
	//SecurityFilterChain : 필터 체인을 구성 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.formLogin()
		//로그인 설정
		.loginPage("/members/login") //로그인 페이지 url 설정
		.defaultSuccessUrl("/") //로그인 성공시 이동할 url 설정
		.usernameParameter("email") //로그인시 사용할 파라미터 이름
		.failureUrl("/members/login/error") //로그인 실패시 이동할 url 설정
		
		.and()
		
		//로그아웃 설정
		.logout() //로그아웃 처리
		.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃에 대한 url 설정
		.logoutSuccessUrl("/"); //로그아웃 성공시 이동할 url 설정
		
		//스프링 시큐리티에 httpServleteRequest를 이용하겠다는 의미
		http.authorizeRequests()
		//모든 사용자가 인증 없이 해당 경로에 접근할 수 있도록 설정
		.mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
		.mvcMatchers("/", "/members/**", "/product/**", "/images/**", "/extras/**").permitAll()
		//admin으로 시작하는 경로는 해당 계정이 관리자일때만 접근 가능하도록 설정
		.mvcMatchers("/admin/**", "/approve/**").hasRole("ADMIN")
		.mvcMatchers("/company/**").hasRole("COMPANY")
		//나머지는 모두다 인증을 요구
		.anyRequest().authenticated();
		
			
			//인증되지 않은 사용자가 리소스에 접근할 때 수행되는 핸들러 목록
			http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
				
		return http.build();
	}
	
	
	
	//CSRF : 사이트간 요청 위조 (쿠키)
	//비밀번호를 암호화하고 저장된 암호와 사용자가 제공한 암호를 비교하는 역할
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //해시함수(비밀번호를 암호화)
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
				AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
