package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	// update
	public int update(BoardVO boardVO) throws Exception;
	// delete
	public int delete(BoardVO boardVO) throws Exception;
	// selectOne
	public BoardVO detail(BoardVO boardVO) throws Exception;
	// selectAll
	public List<BoardVO> list(Pager pager) throws Exception;
	// add
	public int add(BoardVO boardVO) throws Exception;
	// 
	public Long getTotalCount() throws Exception;
	
}
