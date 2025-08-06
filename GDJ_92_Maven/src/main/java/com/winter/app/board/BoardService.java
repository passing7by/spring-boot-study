package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardService {
	// list
	public List<BoardVO> list(Pager pager) throws Exception;
	// detail
	public BoardVO detail(BoardVO boardVO) throws Exception;
	// add
	public int add(BoardVO boardVO) throws Exception;
	// update
	public int update(BoardVO boardVO) throws Exception;
	// delete
	public int delete(BoardVO boardVO) throws Exception;
}
