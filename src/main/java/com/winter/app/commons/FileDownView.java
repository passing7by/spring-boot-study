package com.winter.app.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.BoardFileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownView extends AbstractView {
	@Value("${app.upload}")
	private String path;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("============== custom view =============="); // 이 메서드가 실행되는지 확인
		log.info("{}", model);
		
		BoardFileVO boardFileVO =  (BoardFileVO) model.get("vo");
		
		String filePath = path + model.get("board").toString();
		
		File file = new File(filePath, boardFileVO.getSaveName());
		
		// 총 파일의 크기
		// 파일 다운로드시 시간이 얼마나 남았는지 알려주려면 이게 필요함
		response.setContentLengthLong(file.length());
		
		// 파일 다운 시 파일의 이름을 인코딩
		// 파일 이름은 오리지널 이름으로 할 건데, 한글일 수도 있으므로 인코딩 필수
		String fileName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");
		
		// header 설정
		// 파일을 보낼 때 이미지인지, 음악인지, 문서 등인지에 대한 정보를 담아 보내야 함
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// File을 읽서서 전송
		FileInputStream fi = new FileInputStream(file);
		// client로 연결
		OutputStream os = response.getOutputStream();
		// 전송
		FileCopyUtils.copy(fi, os);
		// 자원 해제
		os.close();
		fi.close();
		
	}
}
