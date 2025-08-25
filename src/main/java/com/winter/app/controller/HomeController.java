package com.winter.app.controller;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
		Enumeration<String> keys = session.getAttributeNames();
		
		// attribute가 몇개 있는지 모르므로 while문 사용
		while(keys.hasMoreElements()) {
			log.info("key : {}", keys.nextElement());
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//		log.info("{}", obj.getClass().getName()); // 출력: SecurityContextImpl -> SecurityContext를 구현한 클래스라는 뜻
		
//		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		
//		Authentication authentication = contextImpl.getAuthentication();
		
//		log.info("Auth : {}", authentication);
		
		// --------------------------------------------------------
		
		// 위의 과정을 한 번에 할 수도 잇음
//		authentication = SecurityContextHolder.getContext().getAuthentication();
		
//		MemberVO memberVO = (MemberVO) authentication.getPrincipal(); // 리턴 타입이 오브젝트이기 때문에 MemberVO 혹은 UserDetail로 형변환
		
		return "index";
	}
	
	// 메서드에 principal 자체를 선언할 수도 있음
	@GetMapping("/info")
	public void info(Principal principal) {
		MemberVO memberVO = (MemberVO) principal;
		
		log.info("{}", principal.getName());
		log.info("{}", memberVO.getUsername());
		
	}
}
