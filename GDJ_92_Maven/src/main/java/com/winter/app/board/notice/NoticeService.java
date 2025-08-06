package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	// 이것도 가능
//	@Autowired
//	private BoardDAO noticeDAO;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = noticeDAO.getTotalCount();
		pager.makeNum(totalCount);
		
		// Pager는 굳이 리턴할 필요 없음
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	@Override
	public int add(BoardVO boardVO) throws Exception {
		return noticeDAO.add(boardVO);
	}
	
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return noticeDAO.delete(boardVO);
	}
}
