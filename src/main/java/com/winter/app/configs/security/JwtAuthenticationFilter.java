package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		
		this.jwtTokenManager = jwtTokenManager;
	}
	
	// HttpServletRequest/Response를 매개변수로 받기 위해 doFilter() 대신 doFilterInternal() 사용
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Token 검증
		
		// 1. 토큰을 꺼내기
		Cookie[] cookies = request.getCookies();
		String token = "";
		for (Cookie c : cookies) {
			if (c.getName().equals("accessToken")) {
				token = c.getValue();
				break;
			}
			
		}
		
		log.warn("token: {}", token);
		// 2. 토큰을 검증
		if(token != null && token.length() != 0) {
			try {
				Authentication authentication = jwtTokenManager.getAuthenticationBytoken(token);			
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.warn("authentication name: {}", authentication.getName());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
				// SecurityException || MalformedException || SignatureException : 유효하지 않은 jwt 서명
				// ExpiredJwtException     : 기간이 만료된 token
				// UnsupportedJwtException : 지원되지 않는 token
				// IllegalArgumentException: 잘못된 토큰
			}
		}
		
		chain.doFilter(request, response);
	}
	
	
	
}
