package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDTO;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 생성자 : DB 연결
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	public int join(MemberDTO dto) {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";
		int success = 0;
		System.out.println("dto.getId() : " + dto.getId());
		try {
			// 쿼리문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getEmail());
			
			// 쿼리문 실행
			success = pstmt.executeUpdate();
			System.out.println("success : " + success);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}
	
	// 로그인
	public boolean login(String id, String pw) {
		String sql = "SELECT * FROM member WHERE id=? AND pw=?";
		boolean success = false; // 로그인 성공 여부
		
		try {
			// 쿼리문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			// 쿼리 실행
			rs = pstmt.executeQuery();
			
			// 결과 확인
			success = rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			resClose();
		}
		
		return success;
	}
	
	// 자원 반납
	public void resClose() {
		try {
			if (rs != null) rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
