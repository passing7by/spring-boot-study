package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정 파일이다
@EnableWebSecurity // 기본 security config 대신 이걸 security config로 사용해라
public class SecurityConfig {
	
	// 정적 자원들을 Security에서 제외
	
	// public이 아닌 디폴트로 선언 -> 왜?
	
	@Bean // 라이브러리로 가져온 것은 Spring이 모르기 때문에 이 객체를 생성하라고 알려주는 것
	WebSecurityCustomizer customizer() {
		// web -> WebSecurity 타입 변수
		return web -> {
			web
				.ignoring()
					.requestMatchers("/css/**", "/js/**", "/vender/**")
					.requestMatchers("/files/**")
					.requestMatchers("/")
					;
			// 이런 요청들이 오면 security에서는 무시하겠다
					
		};
	}
	
	// 인증과 인가(권한 설정) Authentication and Authorization
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable()) // csrf - server에서 응답을 내보낼때 코드를 같이 보내주고, 요청시 요청 정보에 그 코드가 있어야 insert할 수 있게 함
			
			// 권한에 관련된 설정
			.authorizeHttpRequests(auth -> {
				// 권한 잡을 때는 순서 중요
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN") // ROLE_ 는 자동으로 제거되므로 "ADMIN"만 작성
					.requestMatchers("/projects/add", "/projects/update", "/projects/delete").hasAnyRole("MANAGER", "ADMIN")
//					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 이렇게 할 수도 있다~ 참고
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll()
					;
			})
			
			// form 관련 설정
			.formLogin(form -> {
				form
					.loginPage("/member/login")
					// 이거 지금은 주석처리 해도 됨. 왜? 파라미터 이름 기본 설정이 "username", "password"이기 때문
//					.usernameParameter("username")
//					.passwordParameter("password")
					.defaultSuccessUrl("/") // 로그인 성공했을 때 redirect로 이동시킬 url
					.failureUrl("/member/login")
					;
			})
			;
		
		return httpSecurity.build();
	}
}
