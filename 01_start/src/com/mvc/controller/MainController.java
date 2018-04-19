package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainController
 */

// 들어오는 주소
@WebServlet("/")
public class MainController extends HttpServlet {
	
	// 객체 통신 시 사용하는 클래스 시리얼넘버
	private static final long serialVersionUID = 1L;
       
	// get일 경우 이 메서드 수행
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 방식으로 요청 받음");

		// 파라미터 get
		String name = request.getParameter("name");
		System.out.println(name);
		
		// 파라미터 set
		request.setAttribute("result", name);
		// 보낼 곳 설정
		RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");
		// 전송
		rd.forward(request, response);
		
	}

	// post일 경우 이 메서드 수행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 방식으로 요청 받음");
	}

}
