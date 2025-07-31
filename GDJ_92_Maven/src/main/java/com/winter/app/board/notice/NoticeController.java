package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
//	@GetMapping("add") // /notice/add 로 get 요청을 받는다는 뜻
//	public void insert() throws Exception {
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setBoardTitle("title");
//		noticeVO.setBoardContents("contents");
//		noticeVO.setBoardWriter("writer");
//		
//		int result = noticeDAO.insert(noticeVO);
//	}
	
	@GetMapping("list")
	public void list(Model model) throws Exception {
		List<BoardVO> list = noticeService.list();
		
		model.addAttribute("list", list);
		
		System.out.println("notice/list");
		
		// return 해주지 않으면 url 경로를 보고 jsp를 찾아감
	}
	
	@GetMapping("detail")
	public void detail(BoardVO boardVO, Model model) throws Exception {
		NoticeVO noticeVO = (NoticeVO) noticeService.detail(boardVO);
		
		model.addAttribute("NoticeVO", noticeVO);
		
		System.out.println("notice/detail");
	}
}
