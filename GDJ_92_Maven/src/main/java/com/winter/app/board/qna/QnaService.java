package com.winter.app.board.qna;

import java.util.ArrayList;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String board;
	
	@Override
	public List<BoardVO> list(Pager pager) throws Exception {
		// TODO 검색 결과가 없어서 totalCount = 0 인 경우 처리
		Long totalCount = qnaDAO.getTotalCount(pager);
		
		// 검색 결과가 없을 경우 빈 list 전달
		if(totalCount <= 0) {
			return new ArrayList<BoardVO>();
		}
		
		pager.makeNum(totalCount);
		
		return qnaDAO.list(pager);
	}

	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		return qnaDAO.detail(boardVO);
	}
	
	public int reply(QnaVO qnaVO) throws Exception {
		QnaVO parent = (QnaVO) qnaDAO.detail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		// update 쿼리 실행
		int result = qnaDAO.refUpdate(parent);
		// insert 쿼리 실행
		result = qnaDAO.replyInsert(qnaVO);
		
		return result;
	}
	
	@Override
	public int add(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		// 1. 글 저장
		int result = qnaDAO.add(boardVO);
		// ref값을 update
		result = qnaDAO.refUpdate(boardVO);
		
		// 만약 화면에 파일 추가 폼조차 없었다면 파일 저장 로직 실행하지 않음
		if(attaches == null) {
			return result;
		}
		
		// 2. HDD에 파일 저장
		for (MultipartFile m : attaches) {
			// 만약 파트에 파일이 없는 경우 파일 저장 로직을 실행하지 않음
			if(m.isEmpty() || m == null) {
				continue;
			}
			String saveName = fileManager.fileSave(upload + board, m);
			// 3. 파일 데이터 저장
			BoardFileVO vo = new BoardFileVO();
			vo.setBoardNum(boardVO.getBoardNum());
			vo.setOriName(m.getOriginalFilename());
			vo.setSaveName(saveName);
			result = qnaDAO.addFile(vo);
		}

		return result;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// db에서 아예 삭제하는 것이 아닌, active 컬럼값만 추가
		// TODO 삭제 기능
		return 0;
	}

	@Override
	public int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
