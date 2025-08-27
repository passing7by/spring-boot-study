package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("{}", exception);
		
		// InternalAuthenticationServiceException  :  아이디가 틀린 경우
		// BadCredentialsException                 :  비밀번호가 틀린 경우
		
		// DisabledException                       :  유효하지 않은 사용자입니다.
		// AccountExpiredException                 :  사용자 계정의 유효 기간이 만료 되었습니다.
		// LockedException                         :  사용자 계정이 잠겨 있습니다.
		// CredentialsExpiredException             :  자격 증명 유효 기간이 만료되었습니다.
		
		String message = "관리자에게 문의해주세요.";
//		if(exception.getClass().getName().equals("BadCredentialsException"))
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "아이디가 틀렸습니다.";
		}
		if(exception instanceof BadCredentialsException) { // instanceof 사용하면 더 간단
			message = "비밀번호가 틀렸습니다.";
		} 
		if(exception instanceof DisabledException) {
			message = "유효하지 않은 사용자입니다.";
		} 
		if(exception instanceof AccountExpiredException) {
			message = "사용자 계정의 유효 기간이 만료 되었습니다.";
		} 
		if(exception instanceof LockedException) {
			message = "사용자 계정이 잠겨 있습니다.";
		} 
		if(exception instanceof CredentialsExpiredException) {
			message = "자격 증명 유효 기간이 만료되었습니다.";
		} 
		if(exception instanceof AuthenticationCredentialsNotFoundException) {
			message = "인증 요청이 거부되었습니다. 관리자에게 문의해주세요.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		response.sendRedirect("./login?failMessage=" + message);
	}

}
 