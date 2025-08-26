package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	// 로그인이 성공했을 때 실행
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null && rememberId.equals("1")) {
			String username = request.getParameter("username");
			
			Cookie c = new Cookie("rememberId", username);
			c.setMaxAge(60 * 60 * 24 * 7);
			c.setPath("/member/login"); // 이 쿠키를 사용 가능한 url 설정
			response.addCookie(c);
		} else {
			for (Cookie c : request.getCookies()) {
				if (c.getName().equals("rememberId")) {
					c.setValue(null);
					c.setMaxAge(0);
					c.setPath("/member/login");
					response.addCookie(c);
					
					// 쿠키 시간을 0으로 설정 & path 설정 & repose에 쿠키 추가 모두 해야 쿠키가 삭제됨
					// jsp에서 쿠키의 value가 있냐/없냐로 체크/체크해제를 하므로 value를 null로 설정해서 보냄
					
					break;
				}
			} 
		}
		
		response.sendRedirect("/");
		
	}
	
}
