package com.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.BbsService;

@WebServlet({ "/", "*.do" })
public class FrontController extends HttpServlet {
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
		
		BbsService service = new BbsService(request, response);
		
		// 커맨드 추출
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		switch(command) {
		case "/" :
			service.list();
			break;
			
		case "/list.do" :
			service.list();
			break;
			
		case "/writeForm.do" :
			service.writeForm();
			break;
			
		case "/write.do" :
			service.write();
			break;
			
		case "/detail.do" :
			service.detail();
			break;
			
		case "/delete.do" :
			service.delete();
			break;
			
		case "/modifyForm.do" :
			service.modifyForm();
			break;
			
		case "/modify.do" :
			service.modify();
			break;
			
		default :
			System.out.println("command 오류");
		}
		
	}

}
