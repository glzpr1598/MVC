package com.kh.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.PhotoDAO;
import com.kh.dto.PhotoDTO;

public class PhotoService {
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public PhotoService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	// 리스트 가져오기
	public void list() throws ServletException, IOException {
		//DB 이용 해서 데이터 가져오기
		PhotoDAO dao = new PhotoDAO();
		ArrayList<PhotoDTO> list = dao.list();
		
		// 페이지로 이동
		request.setAttribute("list", list);
		RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
		dis.forward(request, response);
	}
	
	// 상세보기
	public void detail() throws ServletException, IOException {
		PhotoDAO dao = new PhotoDAO();
		PhotoDTO dto = dao.detail(request.getParameter("idx"));
		
		request.setAttribute("info", dto);
		RequestDispatcher dis = request.getRequestDispatcher("detail.jsp");
		dis.forward(request, response);
	}

	// 글쓰기
	public void write() {
		// PhotoUpload에게 request 전달
		PhotoUpload upload = new PhotoUpload(request);
		// 사진 등록, 데이터 가져오기
		PhotoDTO dto = upload.regist();
		
		
		
		
	}

}
