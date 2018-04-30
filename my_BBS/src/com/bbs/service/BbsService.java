package com.bbs.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.BbsDAO;
import com.bbs.dto.BbsDTO;

public class BbsService {
	
	HttpServletRequest request;
	HttpServletResponse response;

	// 생성자
	public BbsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	// 페이지 이동
	public void movePage(String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	// 글 목록
	public void list() throws ServletException, IOException {
		BbsDAO dao = new BbsDAO();
		ArrayList<BbsDTO> list = dao.list();
		request.setAttribute("list", list);
		
		String page = "list.jsp";
		movePage(page);
	}

	// 글쓰기 창으로 이동
	public void writeForm() throws ServletException, IOException {
		String page = "write.jsp";
		movePage(page);
	}
	
	// 글 쓰기
	public void write() throws IOException {
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = new BbsDTO();
		dto.setbName(request.getParameter("bName"));
		dto.setbTitle(request.getParameter("bTitle"));
		dto.setbContent(request.getParameter("bContent"));
		int success = dao.write(dto);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (success > 0) {
			out.println("alert('등록되었습니다.');");
		} else {
			out.println("alert('실패했습니다.');");
		}
		out.println("location.href='list.do';");
		out.println("</script>");
		out.close();
		
	}

	// 글 상세보기
	public void detail() throws ServletException, IOException {
		// 글번호 파라미터로 get
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BbsDAO dao = new BbsDAO();
		BbsDTO dto = dao.detail(bId);
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
	}

	// 글 삭제
	public void delete() throws IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BbsDAO dao = new BbsDAO();
		int success = dao.delete(bId);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (success > 0) {
			out.println("alert('삭제 완료');");
		} else {
			out.println("alert('삭제 실패');");
		}
		out.println("location.href='./list.do';");
		out.println("</script>");
		out.close();
	}

	// 수정 페이지로 이동
	public void modifyForm() throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BbsDTO dto = new BbsDTO();
		dto.setbId(bId);
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("./modify.jsp");
		rd.forward(request, response);
	}

	// 글 수정
	public void modify() throws IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BbsDTO dto = new BbsDTO();
		dto.setbId(bId);
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		
		BbsDAO dao = new BbsDAO();
		int success = dao.modify(dto);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (success > 0) {
			out.println("alert('수정 완료');");
		} else {
			out.println("alert('수정 실패');");
		}
		out.println("location.href='./detail.do?bId=" + bId + "';");
		out.println("</script>");
		out.close();
	}

}
