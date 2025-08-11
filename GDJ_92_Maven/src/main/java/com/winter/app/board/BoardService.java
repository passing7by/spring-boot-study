package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

public interface BoardService {
	// list
	public List<BoardVO> list(Pager pager) throws Exception;
	// detail
	public BoardVO detail(BoardVO boardVO) throws Exception;
	// add
	public int add(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	// update
	public int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	// delete
	public int delete(BoardVO boardVO) throws Exception;
	// fileDelete
	public int fileDelete(BoardFileVO boardFileVO) throws Exception;
	// fileDetail
	public BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception;
	// boardFileUpload
	public String boardFile(MultipartFile bf) throws Exception;
	// boardFileDelete
	public boolean boardFileDelete(String fileName) throws Exception;
}
