package com.winter.app.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@GetMapping("add") // /notice/add 로 get 요청을 받는다는 뜻
	public void insert() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title");
		noticeVO.setBoardContents("contents");
		noticeVO.setBoardWriter("writer");
		
		int result = noticeDAO.insert(noticeVO);
	}
}
