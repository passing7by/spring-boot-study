package com.winter.app.boards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	// dependancy injection
	@Autowired
	private BoardService boardService;
	
	// 원래는 개발자가 직접 new 로 dependancy injection 해야 함
//	public BoardController() {
//		this.boardService = new BoardService();
//	}
	
	// /board/list 
	// /board/detail
	
	@RequestMapping(value = "list", method = RequestMethod.GET) // value = "/board/list"
	public String list() {
		System.out.println("list");
		boardService.list();
		return "board/list";
	}
	
//	@RequestMapping(value = "detail", method = RequestMethod.GET)
//	public void detail(HttpServletRequest request) { // req, res 를 받아올 수 있고, 필요 없으면 안 받아도 됨
//		String num = request.getParameter("num");
//		// req는 랜선을 타고 전기 신호로 받아오는데(0과 1), 이 때 어떤 타입인지 알 수 없기 때문에 모두 String 타입으로 퉁치는 것임
//		int n = Integer.parseInt(num);
//		System.out.println("detail: " + n);
////		return "board/detail"; // return해주지 않는다면, url 경로를 보고 jsp를 찾아감
//	}
	
//	@RequestMapping(value = "detail", method = RequestMethod.GET)
//	public void detail(Integer num, String kind) { // Spring이 파라미터를 매개변수로 받을 수 있게 함
//		// String num = request.getParameter("num");
//		// int n = Integer.parseInt(num); // int 타입으로 파라미터를 받아오므로 parse 생략 가능
//		BoardVO boardVO = new BoardVO();
//		boardVO.setName(kind);
//		boardVO.setNum(num);
//		System.out.println("detail: " + boardVO);
//	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail(BoardVO boardVO) {
		// 파라미터 이름 = VO의 setter에서 set을 빼고 맨앞글자를 소문자로 바꾼 것
		// 파라미터 이름과 멤버 변수 이름을 동일하게 만들어 주면 됨
		System.out.println("detail: " + boardVO);
	}
}
