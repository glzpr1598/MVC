package com.kh.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadService {

	public String upload(HttpServletRequest request) {
		
		String photoPath = "upload/";
		
		// 용량 제한
		int maxSize = 10*1024*1024;
		
		// 저장경로 설정
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);
		
		// 없으면 폴더 생성
		String uploadPath = root + "upload";
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			System.out.println("폴더 생성");
			dir.mkdir();
		}
		
		// request에서 파일 추출 -> 서버에 저장
		try {
			// MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy) // 파일 업로드
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
			// MultipartRequest도 Request처럼 파라미터 get 가능
			System.out.println("title : " + multi.getParameter("title"));
			
			/* 파일명 변경 */
			// 기존 파일명
			String oriFileName = multi.getFilesystemName("uploadFile"); // uploadFile : 파라미터 name
			
			// .확장자 get
			String ext = oriFileName.substring(oriFileName.indexOf("."));
			System.out.println("확장자 : " + ext);
			
			// 현재시간으로 파일명 생성
			String newFileName = System.currentTimeMillis() + ext;
			System.out.println("새 파일명 : " + newFileName);
			
			// 파일명 변경
			File oldFile = new File(uploadPath + "/" + oriFileName);
			File newFile = new File(uploadPath + "/" + newFileName);
			oldFile.renameTo(newFile);
			photoPath += newFileName;
			System.out.println("반환값 : " + photoPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return photoPath;
	}

}
