package com.kh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kh.dto.PhotoDTO;

public class PhotoDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 생성자 : DB 연결
	public PhotoDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void resClose() {
		try {
			if(rs != null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<PhotoDTO> list() {
		ArrayList<PhotoDTO> list = new ArrayList<PhotoDTO>();		
		try {
			String sql = "SELECT * FROM bbs ORDER BY idx DESC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhotoDTO dto = new PhotoDTO();
				
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();//자원 반납
		}	
		return list;
	}
	
	public PhotoDTO detail(String idx) {
		PhotoDTO dto = null;
		String sql = "SELECT * FROM bbs WHERE idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				upHit(idx); // 조회수 올리기
				dto = new PhotoDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getDate("REG_DATE"));
				dto.setbHit(rs.getInt("bHit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			resClose();
		}
		return dto;
	}

	// 조회수 올리기
	private void upHit(String idx) {
		String sql = "UPDATE bbs SET bHit=bHit+1 WHERE idx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
