package com.winter.app.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.notice.NoticeVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest // 객체를 만들면서 테스트용임을 알리는 어노테이션
@Slf4j
class ProductDAOTest {
	@Autowired
	private ProductDAO productDAO;

//	@Test
//	void listTest() throws Exception {
//		List<BoardVO> result = noticeDAO.list();
//		
//		log.info("result: {}", result);
//		
//		assertTrue(result.size() > 0);
//	}
	
	@Test
	void detailTest() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(2L);
		ProductVO result = (ProductVO) productDAO.detail(productVO);
		
		// 로그
		log.info("result: {}", result); // result를 {} 안에서 찍겠다는 뜻
		
		// 단정문
		assertNotNull(result);
	}
	
	@Test
	void addTest() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setKindNum(1L);
		productVO.setProductContents("contents");
		productVO.setProductName("name");
		productVO.setProductRate(1.2);
		int result = productDAO.add(productVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void updateTest() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(2L);
		productVO.setKindNum(3L);
		productVO.setProductContents("contents");
		productVO.setProductName("name");
		productVO.setProductRate(1.2);
		int result = productDAO.update(productVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void deleteTest() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(9L);
		int result = productDAO.delete(productVO);
		
		// 단정문
		assertEquals(1, result);
	}

}
