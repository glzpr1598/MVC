package com.ajax.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/login")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 파라미터 get */
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id + " / " + pw);
		
		/* ID, PW 일치 여부 확인 */
		int result = 0;
		if (id.equals("admin") && pw.equals("pass")) {
			result = 1;
		} 
		
		/* json 형태로 변환(gson 라이브러리 필요) */
		// Gson 객체 생성
		Gson gson = new Gson();
		// HashMap에 반환할 데이터 담음
		HashMap<String, Integer> map = new HashMap<>();
		map.put("success", result);
		// json형태(String)으로 변환
		String json = gson.toJson(map);
		System.out.println(json);
		
		/* response로 반환 */
		// 옵션1 : 인코딩
		response.setContentType("text/html; charset=UTF-8");
		// 옵션2 : Cross domain 허용
		response.setHeader("Access-Control-Allow-Origin", "*"); // 2번째 인자 : 허용할 도메인
		// 반환
		response.getWriter().println(json);
		
	}

}
