package com.kh.service;

import javax.servlet.http.HttpServletRequest;

import com.kh.dto.PhotoDTO;

public class PhotoUpload {

	HttpServletRequest request;
	String savePath = null;
	
	public PhotoUpload(HttpServletRequest request) {
		this.request = request;
	}

	// 사진 등록
	public PhotoDTO regist() {
		PhotoDTO dto = new PhotoDTO();
		
		
		return null;
	}

}
