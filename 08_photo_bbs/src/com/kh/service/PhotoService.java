package com.kh.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.dao.PhotoDAO;
import com.kh.dto.BoardDTO;

public class PhotoService {
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	public PhotoService(HttpServletRequest request, 
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	//리스트 호출 하기
	public void list() throws ServletException, IOException {
		//DB 이용 해서 데이터 가져오기
		PhotoDAO dao = new PhotoDAO();
		ArrayList<BoardDTO> list = dao.list();
		//가져온 데이터를 request 에 담기
		request.setAttribute("list", list);
		//특정한 페이지로 이동		
		RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
		dis.forward(request, response);
	}

	//상세 보기
	public void detail() throws ServletException, IOException {
		//DB 에 개별 데이터 요청
		PhotoDAO dao = new PhotoDAO();
		BoardDTO dto = dao.detail(request.getParameter("id"));
		request.setAttribute("info", dto);
		//특정한 페이지로 이동		
		RequestDispatcher dis = request.getRequestDispatcher("detail.jsp");
		dis.forward(request, response);
	}

	//글쓰기 페이지
	public void write() throws IOException {		
		//1.PhotoUpload 에게 request 를 전달
		PhotoUpload upload = new PhotoUpload(request);
		//2. regist 메서드를 사용해서 사진 등록
		//3. 제목,내용,글쓴이,파일명을 받는다.BoardDTO
		BoardDTO dto = upload.regist();
		System.out.println(dto.getContent());
		System.out.println(dto.getNewFileName());
		//4. DAO 에 dto 를 전달하여 DB 에 추가해 달라고 요청
		PhotoDAO dao = new PhotoDAO();
		int pk = dao.write(dto);		
		//5. 결과에 따라 페이지 이동	
		//실패(글쓰기 폼)
		String page = "writeForm.jsp";
		if(pk > 0) {
			//성공(상세보기) = 글쓰기 한 후 idx 반환
			page = "detail?id="+pk;
		}		
		response.sendRedirect(page);
	}

	//글 삭제
	public void del() throws IOException {
		//파라메터 추출
		String id = request.getParameter("id");
		PhotoDAO dao = new PhotoDAO();
		//글 번호로 파일명 추출(DB)
		String fileName = dao.fileNameCall(Integer.parseInt(id));
		//글 삭제(DB)
		if(dao.del(id)>0) {
			//파일 삭제(파일이 있을 경우만)
			if(fileName != null) {
				PhotoUpload photo = new PhotoUpload(request);
				photo.del(fileName);
			}
		}		
		//페이지 이동(리스트)
		response.sendRedirect("./list");
	}

	//수정 보기
	public void updateForm() throws ServletException, IOException {
		String idx = request.getParameter("idx");
		//상세정보 가져오기(DB)
		PhotoDAO dao = new PhotoDAO();
		BoardDTO dto = dao.detail(idx);
		//수정 보기 페이지에 뿌려 준다.
		request.setAttribute("dto", dto);
		RequestDispatcher dis = request.getRequestDispatcher("updateForm.jsp");
		dis.forward(request, response);
	}

	//수정 하기
	public void update() throws IOException {
		//PhotoUpload 의 regist() 실행
		PhotoUpload upload = new PhotoUpload(request);
		BoardDTO dto = upload.regist();
		//올린 파일이 있는가? 기존 파일 명은?
		PhotoDAO dao = new PhotoDAO();
		String oldFileName = null;		
		dao.update(dto);//DB 이용 해서 글 수정
		System.out.println("newFileName : "+dto.getNewFileName());
		if(dto.getNewFileName() != null) {//새로 올리는 파일이 있을 경우
			//올린파일이 있다는 것은 기존 파일을 지워야 하는 것을 의미하므로 파일명을 알아야 한다.
			oldFileName = dao.fileNameCall(dto.getIdx());		
			dao = new PhotoDAO();//update() 에서 자원을 닫았으므로 다시 객체화			

			//파일명을 DB 에서 수정
			dao.fileNameUpdate(dto.getIdx(),dto.getNewFileName(),oldFileName);
			//기존 파일을 폴더에서 삭제	
			upload.del(oldFileName);			
		}
		response.sendRedirect("detail?id="+dto.getIdx());
	}

}



