package com.winter.app.board.qna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/qna/*")
@Slf4j
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String name;

	// 이 코드는 controller 안의 모든 메서드가 실행될 때마다 같이 실행됨
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
//		model.addAttribute("board", "Q&A");
		model.addAttribute("list", qnaService.list(pager));
		
		System.out.println("/qna/list");
		
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		System.out.println("/qna/add");
		
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(BoardVO boardVO, Model model, MultipartFile[] attaches) throws Exception {
		int result = qnaService.add(boardVO, attaches);
		String url = "./list";
		String msg = "등록 실패";
		
		if(result != 0) {
			msg = "등록 성공";
			System.out.println("qna/add - Post: 성공");
		}
		else {
			System.out.println("qna/add - Post: 실패");
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("detail")
	public String detail(BoardVO boardVO, Model model) throws Exception {
		BoardVO result = qnaService.detail(boardVO);
		
		model.addAttribute("vo", result);
		
		System.out.println("qna/detail");
		
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String reply(BoardVO boardVO, Model model) throws Exception {
		model.addAttribute("vo", boardVO);
		
		System.out.println("qna/add");
		
		return "board/add";
	}
	
	@PostMapping("reply")
	public String reply(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.reply(qnaVO);
		String url = "./list";
		String msg = "등록 실패";
		
		if(result != 0) {
			msg = "등록 성공";
			System.out.println("qna/reply - Post: 성공");
		}
		else {
			System.out.println("qna/reply - Post: 실패");
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
}
