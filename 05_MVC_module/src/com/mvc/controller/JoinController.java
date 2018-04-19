package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.MemberDTO;
import com.mvc.service.JoinService;

@WebServlet("/Join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 확인
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		System.out.println(id + " / " + pw + " / " + name + " / " + age + " / " + gender + " / " + email);
		
		// 누구에게 일을 시킬 것인지
		JoinService service = new JoinService();
		
		// 데이터 전달
		// 데이터 전송을 효율적으로 하기 위해 Beans(DTO) 이용(데이터를 모아놓은 class)
		MemberDTO dto = new MemberDTO(id, pw, name, age, gender, email);
		
		// 뷰에게 전달
	}

}
	