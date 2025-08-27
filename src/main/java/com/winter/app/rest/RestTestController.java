package com.winter.app.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/api/*")
public class RestTestController {

	@GetMapping("/list")
	public void m1() throws Exception {
//		log.info("api list");
//		
//		RestTemplate template = new RestTemplate();
//		
//		HttpEntity<MultiValueMap<String, String>> param = new HttpEntity<>(null);
//		
//		List<PhotoVO> result = template.getForObject("https://jsonplaceholder.typicode.com/photos", List.class, param);
		
		WebClient webClient = WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com/photos/1")
				.build();

		Mono<ResponseEntity<PhotoVO>> res = webClient.get()
				.retrieve()
				.toEntity(PhotoVO.class)
				;
		PhotoVO photoVO = res.block().getBody();
		
		
		log.info("{}", photoVO);
		
		this.m2();
	}
	
	private void m2() throws Exception {
		WebClient webClient = WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.build();
		
		Flux<UserVO> res = webClient.get()
				.uri("/users")
				.retrieve()
				.bodyToFlux(UserVO.class)
				;
		
		log.info("{}", res.blockFirst());
	}
}
