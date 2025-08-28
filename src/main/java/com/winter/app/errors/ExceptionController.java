package com.winter.app.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역으로 예외를 처리하기 위한 클래스
// 개발할 때는 에러를 보아야 하므로 개발 완료 후 배포 전에 적용해야 함

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(exception = NullPointerException.class)
	public String error(Model model, NullPointerException ex) { // Model, Exception 받을 수 있음
		
		return "errors/error";
	}
	
	@ExceptionHandler(exception = NumberFormatException.class)
	public String error2() {
		return "errors/error";
	}
	
	@ExceptionHandler(exception = Exception.class)
	public String error3() {
		return "errors/error";
	}
	
	@ExceptionHandler(exception = Throwable.class)
	public String error4() {
		return "errors/error";
	}
	
}
