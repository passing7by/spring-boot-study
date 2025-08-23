package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;

@Mapper // Spring이 이 인터페이스를 구현하면서 메서드 오버라이딩 + 객체 구현까지 해 줌
public interface NoticeDAO extends BoardDAO {

}
