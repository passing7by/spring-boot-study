package com.winter.app.configs.security;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
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
		
		Cookie refresh = null;
		
		if(cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("accessToken")) {
					token = c.getValue();
//					refresh
					break;
				}
				
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

				// ExpiredJwtException이 발생했을 때 refresh token 검사
				if (e instanceof ExpiredJwtException) {
					System.err.println("기간 만료");
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("refreshToken")) {
							String newToken = cookie.getValue();
							System.err.println("리프레쉬 토큰 일치");
							try {
								Authentication authentication = jwtTokenManager.getAuthenticationBytoken(newToken);
								SecurityContextHolder.getContext().setAuthentication(authentication);
								newToken = jwtTokenManager.makeAccessToken(authentication);
								
								// 새로운 access 쿠키로 넣어줌
								Cookie c = new Cookie("accessToken", newToken);
								c.setPath("/");
								c.setMaxAge(180);
								c.setHttpOnly(true); // js에서 쿠키를 변경하지 못하도록 함
								
								response.addCookie(c);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}
	
	
	
}
