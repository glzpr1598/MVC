package com.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.BbsService;

@WebServlet({ "/login", "/logout", "/list", "/write", "/delete", "/detail", "/updateForm", "/update" })
public class BbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	private void dual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String sub = uri.substring(cp.length());
		
		BbsService service = new BbsService(request, response);
		
		switch (sub) {
		case "/login" :
			System.out.println("login 요청");
			service.login();
			break;
		case "/logout" :
			System.out.println("logout 요청");
			service.logout();
			break;
		case "/list" :
			System.out.println("list 요청");
			service.list();
			break;
		case "/write" :
			System.out.println("write 요청");
			service.write();
			break;
		case "/delete" :
			System.out.println("delete 요청");
			service.delete();
			break;
		case "/detail" :
			System.out.println("detail 요청");
			service.detail();
			break;
		case "/updateForm" :
			System.out.println("updateForm 요청");
			service.updateForm();
			break;
		case "/update" :
			System.out.println("update 요청");
			service.update();
			break;
		default :
			System.out.println("Input error");
		}
	}
	
}
