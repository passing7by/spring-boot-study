package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy // 이걸 써야 aop가 작동(생략해도 작동)
@ServletComponentScan // 스프링 부트에서 @WebFilter 어노테이션을 사용하여 filter 설정을 하는 경우, Application.java에서 이 어노테이션을 반드시 붙여야 함
@EnableScheduling
public class Gdj92MavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj92MavenApplication.class, args);
	}

}
