package com.winter.app.commons;

import lombok.Getter;
import lombok.Setter;

// @Component // 지금은 쓰지 않음... 왜? TODO 설명 추가
@Getter
@Setter
public class Pager {
	// limit의 시작 인덱스 번호
	private Long startIndex;
	
	// limit의 끝 인덱스 번호... oracle db 사용할 때도 고려해서 추가한 필드
	private Long endIndex;
	
	// 페이지당 보여줄 데이터의 갯수
	private Long perPage;
	
	// page 번호
	// pageNum은 화면에서 보내는 파라미터 값을 받아서 사용할 수도있기 때문에 필드로 선언
	private Long pageNum;
	
	private Long totalPage;
	
	private Long startNum;
	private Long endNum;
	
	//totalCount는 한 번만 쓸 거기 때문에 굳이 메모리 아깝게 필드에 선언 X
	private void makePage() {
		// SQL의 Limit값을 계산
		this.startIndex = (pageNum -1) * perPage;
		this.endIndex = perPage; // 위에서 getter를 호출했기 때문에 아래에선 안 해도 됨
	}
	
	public void makeNum(Long totalCount) {
		// 1. totalPage: 전체 페이지 개수
		this.totalPage = totalCount / this.getPerPage();
		if (totalCount % perPage != 0) {
			this.totalPage++;
		}
		
		// 2. totalBlock: 전체 블럭(페이지들의 묶음)의 갯수
		Long perBlock = 5L; // 블럭당 출력할 번호의 개수
		Long totalBlock = totalPage / perBlock;
		if (totalPage % perBlock != 0) {
			totalBlock++;
		}
		
		// 3. 현재 페이지 번호로 현재 블럭 번호를 계산
		Long curBlock = this.getPageNum()/perBlock;
		if (this.pageNum % 5 != 0) {
			curBlock++;
		}
		
		// 4. 현재 블럭의 시작 페이지 번호와 끝 페이지 번호 계산
		this.startNum = (curBlock -1) * perBlock + 1;
		this.endNum = curBlock * perBlock;
		
		// 5. 마지막 블럭일 경우
		if (curBlock == totalBlock) {
			this.endNum = totalPage;
		}
		
		this.makePage();
	}

	public Long getPerPage() {
		if (this.perPage == null) {
			this.perPage = 10L;
		}
		
		return perPage;
	}

	public Long getPageNum() {
		if (this.pageNum == null || this.pageNum < 1) { // this.pageNum < 1: pageNum ≤ 0일 때의 처리
			this.pageNum = 1L;
		}
		
		if (this.pageNum > totalPage) {
			this.pageNum = totalPage;
		}
		
		return pageNum;
	}
	
}
