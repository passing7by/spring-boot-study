package com.winter.app.board.qna;
import com.winter.app.board.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value = "/qna/*")
public class QnaController {

    private final NoticeService noticeService;
	@Autowired
	private QnaService qnaService;

    QnaController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
	
	@GetMapping("list")
	public String list(Model model) throws Exception {
		model.addAttribute("list", qnaService.list());
		
		System.out.println("/qna/list");
		
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		System.out.println("/qna/add");
		
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(BoardVO boardVO, Model model) throws Exception {
		int result = qnaService.add(boardVO);
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
