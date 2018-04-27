package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.service.PhotoService;


@WebServlet({ "/error", "/list", "/write", "/updateForm", "/update", "/del", "/detail" })
public class PhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		dual(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dual(request, response);
	}

	private void dual(HttpServletRequest request, 
		HttpServletResponse response) 
			throws ServletException, IOException{
		
		//uri - contextPath = subAddr
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String subAddr = uri.substring(context.length());
		
		PhotoService service = new PhotoService(request, response);
		
		switch(subAddr) {
		case "/list":
			System.out.println("리스트 페이지");
			service.list();
			break;
			
		case "/write":
			System.out.println("글쓰기 페이지");
			service.write();
			break;
			
		case "/updateForm":
			System.out.println("수정 폼 페이지");
			service.updateForm();
			break;
			
		case "/update":
			System.out.println("수정 페이지");
			service.update();
			break;
			
		case "/del":
			System.out.println("삭제 페이지");
			service.del();
			break;
			
		case "/detail":
			System.out.println("상세보기 페이지");
			service.detail();
			break;
			
		case "/error":
			System.out.println("IO Excetion 에러");
			break;
			
		}

		
	}

}
