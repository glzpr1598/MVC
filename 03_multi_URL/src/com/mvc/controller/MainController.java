package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainController
 */
@WebServlet({ "/", "/main", "/index" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서버 접근");
		// "/"가 맵핑되어있어 /뒤에 아무 텍스트가 들어와도 이 메서드가 호출된다.
		// 이를 막기 위해 아래 코드 수행
		
		// 서브 URI 확인(주로 /프로젝트명/경로)
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);
		
		// ContextPath(주로 /프로젝트명) 확인
		String cp = request.getContextPath();
		System.out.println("ContextPath : " + cp);
		
		// 서브 URI - ContextPath = /경로
		String subAddr = uri.substring(cp.length());
		System.out.println(subAddr);
		
		// URL마다 다른 메시지 출력
		switch(subAddr) {
		case "/" :
			System.out.println("root 페이지");
			break;
		case "/main" :
			System.out.println("main 페이지");
			break;
		case "/index" :
			System.out.println("index 페이지");
			break;
		default :
			System.out.println("존재하지 않는 페이지");
		}
	}

}
