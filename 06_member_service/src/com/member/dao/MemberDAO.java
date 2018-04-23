package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	// 멤버 리스트 반환
	public ArrayList<MemberDTO> list() {
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM member";
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// list에 데이터 담기
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("Name"));
				dto.setEmail(rs.getString("Email"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null; // catch 문에 있는 return도 finally 실행 후에 실행된다.
		} finally {
			resClose();
		}
		
		return list;
	}
	
	public int memberDel(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		int success = 0;
		
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			success = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		
		return success;
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

	public MemberDTO detail(String id) {
		String sql = "SELECT * FROM member WHERE id=?";
		MemberDTO dto = null;
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 추출하여 DTO에 담기
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			resClose();
		}
		
		return dto;
	}

	public int update(MemberDTO dto) {
		String sql = "UPDATE member SET pw=?, name=?, age=?, gender=?, email=? WHERE id=?";
		int success = 0;
		try {
			// 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getAge());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getId());
			success = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		return success;
	}

}
