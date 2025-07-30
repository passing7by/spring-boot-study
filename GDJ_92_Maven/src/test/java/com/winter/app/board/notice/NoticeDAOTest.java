package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 객체를 만들면서 테스트용임을 알리는 어노테이션
class NoticeDAOTest {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void insertTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title3");
		noticeVO.setBoardContents("contents3");
		noticeVO.setBoardWriter("writer3");
		int result = noticeDAO.insert(noticeVO);
		
		// 단정문
//		assertEquals(1, result); // Error: 0, Failures: 0
		assertEquals(0, result); // Error: 0, Failures: 1
	}
	
	@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(1L);
		noticeVO.setBoardTitle("title4");
		noticeVO.setBoardContents("contents4");
		int result = noticeDAO.update(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void deleteTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(1L);
		int result = noticeDAO.delete(noticeVO);
		
		// 단정문
//		assertEquals(1, result);
		assertEquals(0, result);
	}

}
