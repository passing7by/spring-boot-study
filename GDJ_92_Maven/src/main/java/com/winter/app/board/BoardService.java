package com.winter.app.board;

import java.util.List;

import com.winter.app.board.notice.NoticeVO;

public interface BoardService {
	// list
	public List<BoardVO> list() throws Exception;
	// detail
	public BoardVO detail(BoardVO boardVO) throws Exception;
}
