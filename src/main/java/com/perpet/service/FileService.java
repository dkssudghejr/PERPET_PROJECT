package com.perpet.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {

	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
		
		//서로 다른 파일을 구분하기 위해 고유한 파일명을 생성
		UUID uuid = UUID.randomUUID();
		
		//확장자 추출
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//고유의 파일명과 확장자를 결합해서 저장될 파일명을 생성
		String savedFileName = uuid.toString() + extension;
		//파일이 업로드될 전체 경로를 생성
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		//입출력스트림을 생성
		//파일 경로에 해당하는 파일을 생성하거나 덮어쓰기 함
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		//해당 내용을 파일에 기록
		fos.write(fileData);
		//입출력 스트림 닫기
		fos.close();
		//저장된 파일 이름을 리턴
		return savedFileName;
	}
	
	//파일 삭제
	public void deleteFile(String filePath) throws Exception{

		//삭제할 파일 객체를 생성함 
		File deleteFile = new File(filePath);
		
		//삭제할 파일이 존재하는지를 확인
		if(deleteFile.exists()) {
			//파일을 삭제
			deleteFile.delete();
			//로그를 출력
			log.info("파일을 삭제하였습니다.");
		}else {
			log.info("파일이 존재하지 않습니다.");
		}
	}
}
