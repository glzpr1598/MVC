package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.service.MemberService;

@WebServlet({ "/join", "/login", "/logout", "/main", "/memberDel", "/updateForm", "/memberUpdate", "/memberDetail" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}
	
	// get, post 모두 처리
	private void dual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러는 URL을 분리하여 service로 보낸다.
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String sub = uri.substring(cp.length());
		System.out.println("sub : " + sub);
		
		MemberService service = new MemberService(request, response);
		
		switch (sub) {
		case "/join" :
			System.out.println("join 요청");
			// service에게 join 요청
			service.join();
			break;
		case "/login" :
			System.out.println("login 요청");
			// service에게 login 요청
			service.login();
			break;
		case "/logout" :
			System.out.println("logout 요청");
			service.logout();
			break;
		case "/main" :
			System.out.println("main 요청");
			service.main();
			break;
		case "/memberDel" :
			System.out.println("memberDel 요청");
			service.memberDel();
			break;
		case "/updateForm" :
			System.out.println("UpdateForm 요청");
			service.updateView();
			break;
		case "/memberUpdate" :
			System.out.println("memberUpdate 요청");
			service.memberUpdate();
			break;
		case "/memberDetail" :
			System.out.println("memberDetail 요청");
			service.memberDetail();
			break;
		default :
			System.out.println("Input error");
		}
	}

}
