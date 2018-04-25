package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.service.PhotoService;

@WebServlet({ "/", "/write", "/writeForm", "/update", "/del", "/detail" })
public class PhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dual(request, response);
	}
	
	private void dual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String context = request.getContextPath();
		System.out.println("context : " + context);
		String command = uri.substring(context.length());
		System.out.println("command : " + command);
		
		PhotoService service = new PhotoService(request, response);
		
		switch (command) {
		case "/" :
			System.out.println("main 요청");
			service.list();
			break;
		case "/write" :
			System.out.println("write 요청");
			service.write();
			break;
		case "/writeForm" :
			System.out.println("writeForm 요청");
			break;
		case "/update" :
			System.out.println("update 요청");
			break;
		case "/del" :
			System.out.println("del 요청");
			break;
		case "/detail" :
			System.out.println("detail 요청");
			service.detail();
			break;
		default :
			System.out.println("command error");
			break;
		}
		
	}
	

}
