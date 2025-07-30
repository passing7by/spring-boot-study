package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.winter.app.boards.BoardController;
import com.winter.app.factory.Arm;
import com.winter.app.factory.GunArm;

@Configuration // 설정 파일 (Spring Container에 Bean을 정의하는 클래스)
public class RobotConfig {
	private final BoardController boardController;
	
	RobotConfig(BoardController boardController) {
		this.boardController = boardController;
	}
	
	// 어노테이션을 줄 수 없고 new 로 직접 생성해서 사용해야 하는 경우 (예를 들면, 외부 라이브러리의 클래스를 가져와야할 경우)
	// 아래와 같이 Bean 임을 명시해 줌
	@Bean
	Arm getGunArm() {
		return new GunArm();
	}
}
