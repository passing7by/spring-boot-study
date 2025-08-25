package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration // 설정 파일이다
@EnableWebSecurity // 기본 security config 대신 이걸 security config로 사용해라
public class SecurityConfig {
	
	// 정적 자원들을 Security에서 제외
	
	// public이 아닌 디폴트로 선언 -> 왜?
	
	@Bean // 라이브러리로 가져온 것은 Spring이 모르기 때문에 이 객체를 생성하라고 알려주는 것
	WebSecurityCustomizer customizer() {
		
	}
	
}
