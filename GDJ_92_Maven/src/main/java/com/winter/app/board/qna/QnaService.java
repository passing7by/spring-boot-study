package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class QnaService implements BoardService {
	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public List<BoardVO> list() throws Exception {
		return qnaDAO.list();
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
	public int add(BoardVO boardVO) throws Exception {
		int result = qnaDAO.add(boardVO);
		// ref값을 update
//		result = qnaDAO.refUpdate(boardVO);
		return result;
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
