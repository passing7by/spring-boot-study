package com.winter.app.boards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	// /board/list 
	// /board/detail
	
	@RequestMapping(value = "list", method = RequestMethod.GET) // value = "/board/list"
	public String list() {
		System.out.println("list");
		return "board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail() {
		System.out.println("detail");
//		return "board/detail"; // return해주지 않는다면, url 경로를 보고 jsp를 찾아감
	}
}
