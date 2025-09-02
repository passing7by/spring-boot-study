package com.winter.app.configs.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;
import com.winter.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

// 토큰을 만들거나 토큰을 검증하는 역할 담당
@Component
public class JwtTokenManager {
	
	// secretKey는 노출 금지, 모든 서버가 같은 값을 가져야 함
	// 사용자의 정보를 암호화할 때 사용
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Value("${jwt.tokenValidTime}")
	private Long tokenValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	private SecretKey key;
	
	@Autowired
	private MemberDAO memberDAO;
	
	// 생성자에서 코드 작성 가능
	@PostConstruct // 생성자 호출 전에 이 메서드를 실행하라는 뜻
	public void init() {
		String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(k.getBytes());
	}
	
	// Token 발급
	public String createToken(Authentication authentication) {
		return Jwts
				.builder()
				.subject(authentication.getName()) // subject: 사용자의 ID(username)
				.claim("roles", authentication.getAuthorities().toString()) // claim: 넣고싶은 정보 추가
				.issuedAt(new Date()) // Token을 생성한 시간
				.expiration(new Date(System.currentTimeMillis() + tokenValidTime))
				.issuer(issuer)
				.signWith(key)
				.compact();
	}
	
	//token 검증
	public Authentication getAuthenticationBytoken(String token) throws Exception {
		Claims claims = Jwts
							.parser()
							.verifyWith(key)
							.build()
							.parseSignedClaims(token)
							.getPayload()
							;
		
		// 검증 통과
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(claims.getSubject());
		UserDetails userDetails = memberDAO.detail(memberVO);
		
		// MemberVO(UserDetail)를 Authentication으로 변경
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		return authentication;
		
		// 검증 실패는 Exception 발생
	}
}
