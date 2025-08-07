package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	public Long getTotalCount(Pager pager) throws Exception;
	// file 메타데이터 저장
	public int addFile(BoardFileVO boardFileVO) throws Exception;
	// file 메타데이터 삭제
	public int fileDelete(BoardVO boardVO) throws Exception;
}
