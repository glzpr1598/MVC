package com.ajax.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.dao.MemberDAO;
import com.ajax.dto.MemberDTO;
import com.google.gson.Gson;

public class MemberSerivce {
	HttpServletRequest request;
	HttpServletResponse response;
	
	public MemberSerivce(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void overlay() throws IOException {
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		boolean overlay = dao.overlay(id);
		
		Gson gson = new Gson();
		
		HashMap<String, Boolean> map = new HashMap<>();
		map.put("overlay", overlay);
		
		String json = gson.toJson(map);
		response.getWriter().println(json);
	}

	public void join() throws IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		MemberDTO dto = new MemberDTO(id, pw, name, age, gender, email);
		MemberDAO dao = new MemberDAO();
		int success = dao.join(dto);
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("success", success);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		response.getWriter().println(json);
	}
	
}
