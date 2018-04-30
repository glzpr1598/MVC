package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bbs.dto.BbsDTO;

public class BbsDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 생성자
	public BbsDAO() {
		// DB 접속
		try {
			Context context = new InitialContext();
			DataSource ds  = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 자원 닫기
	public void resClose() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 전체 리스트 반환
	public ArrayList<BbsDTO> list() {
		ArrayList<BbsDTO> list = new ArrayList<>();
		
		// 모든 글 조회
		String sql = "SELECT * FROM bbs";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// list에 DTO 추가
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				
				BbsDTO dto = new BbsDTO(bId, bName, bTitle, bContent, bDate, bHit);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return list;
	}

	// 글 쓰기
	public int write(BbsDTO dto) {
		// 글 삽입
		String sql = "INSERT INTO bbs(bId, bName, bTitle, bContent) VALUES(seq_bbs.NEXTVAL, ?, ?, ?)";
		int success = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getbName());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			
			success = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}

	// 글 상세보기
	public BbsDTO detail(int bId) {
		upHit(bId);
		
		String sql = "SELECT * FROM bbs WHERE bId=?";
		BbsDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				
				dto = new BbsDTO(bId, bName, bTitle, bContent, bDate, bHit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return dto;
	}

	// 조회수 증가
	private void upHit(int bId) {
		String sql = "UPDATE bbs SET bHit=bHit+1 WHERE bId=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 다른 DAO에서만 호출하기 때문에 자원 반납은 안함.
	}

	// 글 삭제
	public int delete(int bId) {
		String sql = "DELETE FROM bbs WHERE bId=?";
		int success = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}

	// 수정
	public int modify(BbsDTO dto) {
		String sql = "UPDATE bbs SET bName=?, bTitle=?, bContent=? WHERE bId=?";
		int success = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getbName());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			pstmt.setInt(4, dto.getbId());
			
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}
	

}
