package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mvc.dto.MemberDTO;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	// 생성자 : DAO를 객체화 한다는 것은 DB를 사용한다는 것이므로 생성자에서 DB 접속
	public MemberDAO() {
		try {
			// DB 접속
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 받아온 데이터를 DB에 추가하고 결과 반환
	public int join(MemberDTO dto) {
		// 쿼리문 준비
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		int success = 0;
		
		try {
			// PreparedStatment get
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getEmail());
			
			// 쿼리 실행
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납 메서드
			resClose();
		}
		
		return success;
	}

	private void resClose() {
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
