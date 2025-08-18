package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardVO {
	private Long boardNum;
	@NotBlank
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private LocalDate boardDate;
	private Long boardHit;
	
	private List<BoardFileVO> boardFileVOs;
}
