package com.mvc.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainContorller
 */
@WebServlet("/")
public class MainContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opt = request.getParameter("opt");

		// null 일 경우를 먼저 처리해줘야함. -> null을 다른 문자와 비교하면 exception이 발생하기 때문
		if (opt == null) {
			request.setAttribute("result", "Invalid type");
		} else if (opt.equals("greeting")) {
			request.setAttribute("result", "안녕하세요.");
		} else if (opt.equals("date")) {
			request.setAttribute("result", new Date());
		} else {
			request.setAttribute("result", "Input error");
		}

		// 보낼 곳 설정
		RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");
		System.out.println(response.getStatus());
		// 전송
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
