package com.winter.app.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/api/*")
public class RestTestController {

	@GetMapping("list")
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
		
		res.subscribe(u -> {
			UserVO userVO = u; // res에서 하나 꺼낸 걸 UserVO에 넣음
			
			log.info("{}", userVO);
		});
		
	}
	
	@GetMapping("add")
	private void m3() throws Exception {
	// 외부 API(jsonplaceholder)에 post 요청 보내기
		
		// body에 실을 데이터를 vo에 담음
		PostVO postVO = new PostVO();
		postVO.setTitle("title");
		postVO.setBody("contents");
		postVO.setUserId(1L);
		
		// webClient 객체는 .create()로도, .builder()로도 생성할 수 있음
		// .builder()를 사용하면 url 외에도 여러가지 옵션을 부여하여 객체를 생성할 수 있음
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com")
										.build();
		
		// 요청시 메소드명 > url > value > 데이터보내기 의 순서를 지켜줘야 요청 가능
		Mono<PostVO> result = webClient.post()
									.uri("/posts")
									.bodyValue(postVO)
									.accept(MediaType.APPLICATION_JSON) // json 형태로 받겠다... 없어도 작동함
									.retrieve()
								    .bodyToMono(PostVO.class)
								    ;
		
		log.info("{}", result.block());
		
		// api 요청은 프론트에서도, 백에서도 할 수 있지만,
		// 키 정보를 숨겨야할 때는 반드시 백엔드에서 api 요청을 보내야 함
	}
}
