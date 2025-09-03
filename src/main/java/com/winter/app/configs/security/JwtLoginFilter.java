package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
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
	
	// 인증을 시도하는 메서드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UsernamePasswordAuthenticationToken에서 UserDetailService의 loadUserByUsernmae을 호출하고
		// 패스워드가 일치하는지 판별하고 해당 Authentication객체를 SecuritycontextHolder에 담아줌
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	// 로그인 성공시 실행됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// 사용자의 정보로 Token을 생성
		String accessToken = jwtTokenManager.makeAccessToken(authResult);
		String refreshToken = jwtTokenManager.makeRefreshToken(authResult);
		
		Cookie c = new Cookie("accessToken", accessToken);
		c.setPath("/");
		c.setMaxAge(180);
		c.setHttpOnly(true); // js에서 쿠키를 변경하지 못하도록 함
		
		response.addCookie(c);
		
		c = new Cookie("refreshToken", refreshToken);
		c.setPath("/");
		c.setMaxAge(600);
		c.setHttpOnly(true); // js에서 쿠키를 변경하지 못하도록 함
	
		response.addCookie(c);
		
		response.sendRedirect("/");
		
	}
	
	// 로그인 실패시 실행됨
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.err.println(failed.getMessage());
		
		String message = "관리자에게 문의해주세요.";
//		if(failed.getClass().getName().equals("BadCredentialsException"))
		if(failed instanceof InternalAuthenticationServiceException) {
			message = "아이디가 틀렸습니다.";
		}
		if(failed instanceof BadCredentialsException) { // instanceof 사용하면 더 간단
			message = "비밀번호가 틀렸습니다.";
		} 
		if(failed instanceof DisabledException) {
			message = "유효하지 않은 사용자입니다.";
		} 
		if(failed instanceof AccountExpiredException) {
			message = "사용자 계정의 유효 기간이 만료 되었습니다.";
		} 
		if(failed instanceof LockedException) {
			message = "사용자 계정이 잠겨 있습니다.";
		} 
		if(failed instanceof CredentialsExpiredException) {
			message = "자격 증명 유효 기간이 만료되었습니다.";
		} 
		if(failed instanceof AuthenticationCredentialsNotFoundException) {
			message = "인증 요청이 거부되었습니다. 관리자에게 문의해주세요.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("./login?failMessage=" + message);
	}
	
}
