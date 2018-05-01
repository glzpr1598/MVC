package com.ajax.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.dao.BoardDAO;
import com.ajax.dto.BoardDTO;
import com.google.gson.Gson;

public class BoardService {
	HttpServletRequest request;
	HttpServletResponse response;

	public BoardService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	// 리스트
	public void list() throws IOException {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.list();

		String loginId = (String) request.getSession().getAttribute("loginId");
		
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<>();
		map.put("loginId", loginId); // 로그인 아이디
		map.put("list", list); // 게시판 리스트
		String json = gson.toJson(map);
		response.getWriter().println(json);
	}

	// 삭제
	public void delete() throws IOException {
		String[] delList = request.getParameterValues("delList[]");
		
		BoardDAO dao = new BoardDAO();
		int success = dao.delete(delList);
		
		Gson gson = new Gson();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("success", success);
		String json = gson.toJson(map);
		response.getWriter().println(json);
		
	}

	// 상세보기
	public void detail() throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		upHit(idx);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.detail(idx); 
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
	}

	// 조회수 늘리기
	private void upHit(int idx) {
		BoardDAO dao = new BoardDAO();
		dao.upHit(idx);
	}

	// 수정 페이지
	public void updateForm() throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.detail(idx);
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response);
	}

	// 수정
	public void update() throws IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setIdx(idx);
		dto.setSubject(subject);
		dto.setContent(content);
		
		BoardDAO dao = new BoardDAO();
		int success = dao.update(dto);
		
		Gson gson = new Gson();
		HashMap<String, Integer> map = new HashMap<>();
		map.put("success", success);
		String json = gson.toJson(map);
		response.getWriter().println(json);
		
	}

	// 글쓰기 요청
	public void writeForm() throws IOException {
		String loginId = (String) request.getSession().getAttribute("loginId");
		
		Gson gson = new Gson();
		HashMap<String, String> map = new HashMap<>();
		map.put("loginId", loginId);
		String json = gson.toJson(map);
		response.getWriter().println(json);
	}

	// 글쓰기
	public void write() throws IOException {
		int success = 0;
		String loginId = (String) request.getSession().getAttribute("loginId");
		if (loginId != null) {
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			BoardDAO dao = new BoardDAO();
			success = dao.write(loginId, subject, content);
		}
		
		Gson gson = new Gson();
		HashMap<String, Object> map = new HashMap<>();
		map.put("loginId", loginId);
		map.put("success", success);
		String json = gson.toJson(map);
		response.getWriter().println(json);
	}

}
