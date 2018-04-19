package com.mvc.service;

import com.mvc.dao.MemberDAO;

public class JoinService {
	
	public int join() {
		// DB가 필요없다면 직접 처리
		
		// DB가 필요하다면 DAO에게 요청
		MemberDAO dao = new MemberDAO();
		
		return 0;
	}

}
