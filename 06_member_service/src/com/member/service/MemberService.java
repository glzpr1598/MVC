package com.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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

	// 메인 페이지
	public void main() throws IOException, ServletException {
		// DB 접근
		MemberDAO dao = new MemberDAO();
		
		// list를 가져와 request에 담기
		ArrayList<MemberDTO> list = dao.list();
		request.setAttribute("list", list);
		
		// main.jsp 페이지로 전달
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}

	// 로그아웃
	public void logout() throws IOException {
		// 세션 추출
		 HttpSession session = request.getSession();
		// id 속성값 삭제
		session.removeAttribute("id");
		// index 페이지로 이동
		response.sendRedirect("main.jsp");
	}

	// 회원 삭제
	public void memberDel() throws ServletException, IOException {
		// 파라미터 추출
		String id = request.getParameter("id");
		
		// DAO 실행
		MemberDAO dao = new MemberDAO();
		String msg;
		if (dao.memberDel(id) > 0) {
			msg = "삭제 성공";
		} else {
			msg = "삭제 실패";
		}
		
		// 페이지 이동
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("main");
		rd.forward(request, response);
	}

	// 회원정보 상세보기
	public void memberDetail() throws ServletException, IOException {
		// ID 가져오기
		String id = request.getParameter("id");
		
		// DAO, DTO 생성
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.detail(id);
		
		// 페이지 이동
		if (dto != null) {
			request.setAttribute("info", dto);
		} else {
			request.setAttribute("msg", "원하는 값을 찾을 수 없습니다.");
		}
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
		
	}
	
	// 수정 창 보여주기
	public void updateView() throws ServletException, IOException {
		// ID 가져오기
		String id = request.getParameter("id");
		
		// DAO, DTO 생성
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.detail(id);
		
		// 페이지 이동
		if (dto != null) {
			request.setAttribute("info", dto);
		} else {
			request.setAttribute("msg", "원하는 값을 찾을 수 없습니다.");
		}
		RequestDispatcher rd = request.getRequestDispatcher("updateForm.jsp");
		rd.forward(request, response);
	}

	// 회원 수정
	public void memberUpdate() throws ServletException, IOException {
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
		int success = dao.update(dto);

		// 결과 확인
		if (success > 0) {
			// 페이지 이동
			request.setAttribute("msg", "수정 완료");
			RequestDispatcher rd = request.getRequestDispatcher("memberDetail?id=" + id);
			rd.forward(request, response);
		} else {
			// 페이지 이동
			request.setAttribute("msg", "수정 실패");
			RequestDispatcher rd = request.getRequestDispatcher("memberDetail?id=" + id);
			rd.forward(request, response);
		}
		
	}

	

}
