package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String list(Model model) throws Exception {
		List<BoardVO> list = noticeService.list();
		
		model.addAttribute("list", list);
		
		System.out.println("notice/list");
		
		// return 해주지 않으면 url 경로를 보고 jsp를 찾아감
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(BoardVO boardVO, Model model) throws Exception {
		NoticeVO noticeVO = (NoticeVO) noticeService.detail(boardVO);
		
		model.addAttribute("NoticeVO", noticeVO);
		
		System.out.println("notice/detail");
		
		return "board/detail";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		// 페이지 이동만 수행
		
		System.out.println("notice/add");
		
		return "board/add";
	}
	
//	@PostMapping("add")
//	public void add(BoardVO boardVO, HttpServletResponse res) throws Exception {
//		// DB에 데이터 삽입
//		int result = noticeService.add(boardVO);
//		
//		if(result != 0) {
//			System.out.println("notice/add: 성공");
//		} else {
//			System.out.println("notice/add: 실패");
//		}
//		
//		// list로 redirect....
//		res.sendRedirect("list");
//	}
	
	@PostMapping("add")
	public String add(BoardVO boardVO) throws Exception {
		// DB에 데이터 삽입
		int result = noticeService.add(boardVO);
		
		if(result != 0) {
			System.out.println("notice/add: 성공");
		} else {
			System.out.println("notice/add: 실패");
		}
		
		// list로 redirect....
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(NoticeVO noticeVO, Model model) throws Exception {	
		NoticeVO result = (NoticeVO) noticeService.detail(noticeVO);
		model.addAttribute("NoticeVO", result);
		
		System.out.println("notice/update");
		
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(BoardVO boardVO, Model model) throws Exception {
		int result = noticeService.update(boardVO);
		String msg = "수정 실패";
		
		if(result != 0) {
			System.out.println("notice/update: 성공");
			msg = "수정 성공";
		} else {
			System.out.println("notice/update: 실패");			
		}
		
		String url = "./detail?boardNum=" + boardVO.getBoardNum();
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
//		return "redirect:./detail?boardNum=" + boardVO.getBoardNum();
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(BoardVO boardVO, Model model) throws Exception {
		int result = noticeService.delete(boardVO);
		String msg = "삭제 실패";
		
		if(result != 0) {
			System.out.println("notice/delete: 성공");
			msg = "삭제 성공";
		} else {
			System.out.println("notice/delete: 실패");
		}
		
		String url = "./list";
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
}
