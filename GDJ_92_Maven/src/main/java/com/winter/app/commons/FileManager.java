package com.winter.app.commons;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	public String fileSave(String dir, MultipartFile attaches) throws Exception {
		// 1. 디렉토리 생성
		File file = new File(dir);
		
		if(!file.exists()) {
			file.mkdirs(); // mkdir() | mkdirs()
		}
		
		// 2. 저장할 파일명을 생성
		// 중복되지 않는 이름 설정....UUID를 사용할 수도 있고, 밀리세컨즈까지 현재 일시를 사용할 수도 있음
		String fileName = UUID.randomUUID().toString();
		fileName = fileName + "_" + attaches.getOriginalFilename(); // 확장자를 따로 분리하지 않고 그냥 원래 파일명까지 붙여버림
		
		// 3. HDD에 저장
		file = new File(file, fileName); // 아까 썼던 File 객체 재활용
		
		// a. MultipartFile transferTo 메서드 사용
//		attaches.transferTo(file);
		// b. FileCopyUtils의 copy 메서드 사용
		FileCopyUtils.copy(attaches.getBytes(), file);
		
		return fileName;
	}
}
