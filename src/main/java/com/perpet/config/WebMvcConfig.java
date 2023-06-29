package com.perpet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //개발자가 작성한 클래스를 bean으로 등록하고자 할 때 사용
//WebMvcConfigurer : Spring MVC 구성을 사용자가 변경할 수 있도록 메서드를 제공함
public class WebMvcConfig implements WebMvcConfigurer{
	
	//application.properities에 설정한 "uploadPath"의 값을 읽어옴
	@Value("${uploadPath}")
	String uploadPath;
	
	@Override
	//정적 리소스(css, javascript, 이미지)에 대한 요청을 처리하는 방법을 오버라이드함
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//브라우저에 입력하는 url이 /images로 시작하는 경우 
		//uploadPath에 설정한 폴더를 기준으로 파일을 읽어오도록 함
		registry.addResourceHandler("/images/**")
				//컴퓨터 저장된 파일을 읽어올 root 경로를 설정함
				.addResourceLocations(uploadPath);
	}
	
}