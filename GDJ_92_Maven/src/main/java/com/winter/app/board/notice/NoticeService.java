package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	// 이것도 가능
//	@Autowired
//	private BoardDAO noticeDAO;
	
	@Override
	public List<BoardVO> list() throws Exception {
		return noticeDAO.list();
	}
	
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
}
