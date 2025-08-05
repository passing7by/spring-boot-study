package com.winter.app.board.qna;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest // 객체를 만들면서 테스트용임을 알리는 어노테이션
@Slf4j
class QnaDAOTest {
	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Test
	void listTest() throws Exception {
		List<BoardVO> result = qnaDAO.list();
		
		log.info("result: {}", result);
		
		assertTrue(result.size() > 0);
	}
	


}
