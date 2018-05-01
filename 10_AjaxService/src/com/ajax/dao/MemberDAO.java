package com.ajax.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ajax.dto.BoardDTO;
import com.ajax.dto.MemberDTO;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 생성자 : DB 연결
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 자원 반납
	public void resClose() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 중복 체크
	public boolean overlay(String id) {
		String sql = "SELECT id FROM member WHERE id=?";
		boolean overlay = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				overlay = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return overlay;
	}

	// 회원가입
	public int join(MemberDTO dto) {
		String sql = "INSERT INTO member(id, pw, name, age, gender, email) VALUES(?,?,?,?,?,?)";
		int success = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getGender());
			pstmt.setString(6, dto.getEmail());
			
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}

	// 로그인
	public boolean login(String id, String pw) {
		boolean success = false;
		String sql = "SELECT * FROM member WHERE id=? AND pw=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}


}
