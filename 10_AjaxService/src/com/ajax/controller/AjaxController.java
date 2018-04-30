package com.ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.service.BoardService;
import com.ajax.service.MemberSerivce;

@WebServlet({ "/overlay", "/join", "/login", "/logout", "/list", "/detail", "/delete", "/update", "/write" })
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}
	
	private void dual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// command 추출
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		System.out.println(command);
		
		// service 생성
		MemberSerivce mService = null;
		BoardService bService = null;
		
		switch(command) {		
		case "/overlay" :
			System.out.println("중복체크 요청");
			mService = new MemberSerivce(request, response);
			mService.overlay();
			break;
			
		case "/join" :
			System.out.println("회원가입 요청");
			mService = new MemberSerivce(request, response);
			mService.join();
			break;
					
		case "/login" :
			
			break;
			
		case "/logout" :
					
			break;
			
		case "/list" :
			
			break;
			
		case "/detail" :
			
			break;
			
		case "/delete" :
			
			break;
			
		case "/update" :
			
			break;
			
		case "/write" :
			
			break;
			
		default :
			System.out.println("명령어 오류");
			break;
		}
		
	}

}
