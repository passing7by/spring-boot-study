package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 요청시 실행하는 필터
// 토큰으로 로그인 시, UsernamePasswordAuthenticationFilter를 직접 구현해야 함
// username, password를 꺼내서 UserDetailservice의 loadUserByUsername 호출
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		
		this.setFilterProcessesUrl("/member/loginProcess");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Jwt Login Filter ==========");
		System.out.println(username);
		System.out.println(password);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UsernamePasswordAuthenticationToken에서 UserDetailService의 loadUserByUsernmae을 호출하고
		// 패스워드가 일치하는지 판별하고 해당 Authentication객체를 SecuritycontextHolder에 담아줌
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 사용자의 정보로 Token을 생성
		String token = jwtTokenManager.createToken(authResult);
		
		Cookie c = new Cookie("accessToken", token);
		c.setPath("/");
		c.setMaxAge(180);
		c.setHttpOnly(true); // js에서 쿠키를 변경하지 못하도록 함
		
		response.addCookie(c);
		
		response.sendRedirect("/");
		
	}
	
	
}
