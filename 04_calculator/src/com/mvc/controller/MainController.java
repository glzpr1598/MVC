package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	// get, post 어떤 방식으로 와도 같은 코드를 수행
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 get
		double num1 = Double.parseDouble(request.getParameter("num1"));
		double num2 = Double.parseDouble(request.getParameter("num2"));
		String oper = request.getParameter("oper");

		// 계산
		String result;
		switch (oper) {
		case "plus" :
			result = Double.toString(num1 + num2);
			break;
		case "minus" :
			result = Double.toString(num1 - num2);
			break;
		case "multiply" :
			result = Double.toString(num1 * num2);
			break;
		case "divide" :
			result = Double.toString(num1 / num2);
			break;
		default :
			result = "Input error.";
		}
		
		// 결과 result.jsp로 보내기
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");
		rd.forward(request, response);
		
	}

}
