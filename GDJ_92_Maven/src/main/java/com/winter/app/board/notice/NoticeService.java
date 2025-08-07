package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
public class NoticeService implements BoardService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.notice}")
	private String board;
	
	// 이것도 가능
//	@Autowired
//	private BoardDAO noticeDAO;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		Long totalCount = noticeDAO.getTotalCount(pager);
		pager.makeNum(totalCount);
		
		// Pager는 굳이 리턴할 필요 없음
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return noticeDAO.detail(boardVO);
	}
	
	@Override
	public int add(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		int result = noticeDAO.add(boardVO);
		
		for(MultipartFile m : attaches) {
			// 파일이 첨부되지 않았다면 파일 저장 관련 로직을 실행하지 않고 return
			if(m == null || m.isEmpty()) {
				continue;
			}
			
			// 1. File을 HDD에 저장
			String fileName = fileManager.fileSave(upload + board, m);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(m.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(boardVO.getBoardNum());
			result = noticeDAO.addFile(boardFileVO);
			
		}
		
		return result;
	}
	
	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.detail(boardVO);
		
		for(BoardFileVO vo : boardVO.getBoardFileVOs()) {
			fileManager.fileDelete(upload + board, vo.getSaveName());
		}
		
		noticeDAO.fileDelete(boardVO);
		
		return noticeDAO.delete(boardVO);
	}
}
