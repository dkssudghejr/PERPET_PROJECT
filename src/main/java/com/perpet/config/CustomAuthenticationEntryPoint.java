package com.perpet.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

//AuthenticationEntryPoint : 인증되지 않은 요청이 보호받고 있는 리소스에 
//							접근하고자 할 때 호출되는 지점 (인증진입점)
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	//commence : 인증이 필요한 리소스에 접근할 때 호출되며 인증 예외가 발생한 경우에 실행함s
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		// 해당 메서드가 호출되면 HTTP응답을 생성하고 상태코드를 아래와 같이 설정하여
		// 클라이언트에게 메세지를 전달함
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}

}
