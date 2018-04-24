package com.bbs.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.dao.BbsDAO;
import com.bbs.dto.BbsDTO;

public class BbsService {
	HttpServletRequest request;
	HttpServletResponse response;

	public BbsService(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		this.request = request;
		this.response = response;
	}

	public void login() throws IOException, ServletException {
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		System.out.println(id+pw);
		
		//DB 작업이 필요 하기 때문에 BbsDAO 객체화
		BbsDAO dao = new BbsDAO();
		Boolean result = dao.login(id,pw);
		String msg = "아이디 또는 비밀번호를 확인해주세요";
		
		if(result==true) {
			System.out.println("로그인 성공");
			//JAVA에서 내장객체가 아니기 때문에 session 불러오자
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			response.sendRedirect("list");
		}else {
			System.out.println("로그인 실패");
			request.setAttribute("msg", msg);
			RequestDispatcher dis =request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}
	}

	public void logout() throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginId");
		response.sendRedirect("index.jsp");
	}

	public void list() throws IOException, ServletException {
		BbsDAO dao = new BbsDAO();
		ArrayList<BbsDTO> list = new ArrayList<BbsDTO>();
		list =dao.list();
		request.setAttribute("list", list);
		RequestDispatcher dis =request.getRequestDispatcher("list.jsp");
		dis.forward(request, response);
	}

	public void write() throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession(); 
		BbsDAO dao =new BbsDAO();
		BbsDTO dto= new BbsDTO();
		dto.setUser_name((String)session.getAttribute("loginId"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		System.out.println("유저Name : " + dto.getUser_name());
		System.out.println(dto.getSubject());
		System.out.println(dto.getContent());
		if(dao.write(dto)>0) {
			request.setAttribute("msg", "저장 완료");
		}else if(request.getParameter("subject").equals("")) {
			request.setAttribute("msg", "제목 입력해라");
		}else if(request.getParameter("content").equals("")) {
			request.setAttribute("msg", "내용 입력해라");
		}
		RequestDispatcher dis = request.getRequestDispatcher("list");
		dis.forward(request, response);
	}

	public void delete() throws IOException, ServletException {
		//String userId = request.getParameter("user_name");
		int idx = Integer.parseInt(request.getParameter("idx"));
		BbsDAO dao = new BbsDAO();
		String msg = "삭제에 실패 했습니다.";
		if(dao.bbsDel(idx) >0) {
			msg = "삭제에 성공 했습니다.";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher dis = request.getRequestDispatcher("list");
		dis.forward(request, response);
	}
	
	public void detail() throws IOException, ServletException {
		//파라미터 값 받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		//dao 호출
		BbsDAO dao = new BbsDAO();
		
		//detail(idx)
		//dto 반환
		BbsDTO dto = dao.detail(idx);
		
		if(dto != null) {
			request.setAttribute("info", dto);
		}else {
			request.setAttribute("msg", "원하는 게시글을 찾을 수 없습니다");
		}
		RequestDispatcher dis = request.getRequestDispatcher("detail.jsp");
		dis.forward(request, response);
	}

	public void updateForm() throws IOException, ServletException {
		// 글번호 가져오기
		String idx = request.getParameter("idx");
		
		// DAO, DTO 생성
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = dao.detail(Integer.parseInt(idx));
		
		// 페이지 이동
		if (dto != null) {
			request.setAttribute("bbs", dto);
		} else {
			request.setAttribute("msg", "원하는 값을 찾을 수 없습니다.");
		}
		RequestDispatcher rd = request.getRequestDispatcher("updateForm.jsp");
		rd.forward(request, response);
	}

	public void update() throws IOException, ServletException {
		// 수정할 정보 가져오기
		String idx = request.getParameter("idx");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// DAO, DTO 생성
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = new BbsDTO();
		dto.setIdx(Integer.parseInt(idx));
		dto.setSubject(subject);
		dto.setContent(content);
		
		// 데이터 수정
		int success = dao.update(dto);
		
		// 결과 확인
		if (success > 0) {
			request.setAttribute("msg", "수정 완료");
			RequestDispatcher rd = request.getRequestDispatcher("detail?idx=" + idx);
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "수정 실패");
			RequestDispatcher rd = request.getRequestDispatcher("detail?idx=" + idx);
			rd.forward(request, response);
		}
		
	}

	

}
