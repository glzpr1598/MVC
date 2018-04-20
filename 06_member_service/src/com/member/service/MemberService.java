package com.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDAO;
import com.member.dto.MemberDTO;

public class MemberService {
	HttpServletRequest request;
	HttpServletResponse response;
	
	// 생성자
	public MemberService(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		this.request = request;
		this.response = response;
		// 인코딩 설정
		request.setCharacterEncoding("UTF-8");
	}
	
	// 회원가입
	public void join() throws IOException, ServletException {
		// id, pw, name, age, gender, email get
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		// DTO 생성
		MemberDTO dto = new MemberDTO(id, pw, name, age, gender, email);
		
		// DAO로 DTO 전송
		MemberDAO dao = new MemberDAO();
		int success = dao.join(dto);

		// 결과 확인
		if (success > 0) {
			System.out.println("회원가입 성공");
			// 경고창
			// 페이지 이동
			//response.sendRedirect("main");
			request.setAttribute("msg", "회원가입 성공");
			RequestDispatcher rd = request.getRequestDispatcher("main");
			rd.forward(request, response);
		} else {
			System.out.println("회원가입 실패");
			// 페이지 이동
			//response.sendRedirect("joinForm.jsp");
			request.setAttribute("msg", "회원가입 실패");
			RequestDispatcher rd = request.getRequestDispatcher("joinForm.jsp");
			rd.forward(request, response);
		}
	}

	// 로그인
	public void login() throws IOException, ServletException {
		// id, pw get
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + " / " + pw);
		
		// DAO로 id, pw 전송 -> 로그인 결과 반환
		MemberDAO dao = new MemberDAO(); // 객체화 -> DB 접속
		boolean success = dao.login(id, pw);
		
		if (success) {
			System.out.println("로그인 성공");

			// 세션에 아이디 등록
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			// controller의 main으로 이동
			response.sendRedirect("main"); // 페이지만 이동(데이터 전송 x)
			// 데이터도 같이 전송
			/*RequestDispatcher rd = request.getRequestDispatcher("main");
			rd.forward(request, response);*/
		} else {
			System.out.println("로그인 실패");
			// msg 설정
			request.setAttribute("msg", "로그인 실패");
			// index.jsp로 이동(데이터도 같이 전송)
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
