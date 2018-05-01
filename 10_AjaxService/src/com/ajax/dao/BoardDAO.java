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

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 자원 반납
	public void resClose() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 리스트
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<>();
		String sql = "SELECT * FROM bbs ORDER BY idx desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int idx = rs.getInt("idx");
				String user_name = rs.getString("user_name");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date reg_date = rs.getDate("reg_date");
				int bHit = rs.getInt("bHit");

				BoardDTO dto = new BoardDTO(idx, user_name, subject, content, reg_date, bHit);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}

		return list;
	}

	public int delete(String[] delList) {
		int success = 0;
		String sql = "DELETE FROM bbs WHERE idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			for(String temp : delList) {
				pstmt.setString(1, temp);
				success += pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return success;
	}

	// 상세보기
	public BoardDTO detail(int idx) {
		BoardDTO dto = null;
		String sql = "SELECT * FROM bbs WHERE idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String user_name = rs.getString("user_name");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int bHit = rs.getInt("bHit");
				Date reg_date = rs.getDate("reg_date");
				
				dto = new BoardDTO(idx, user_name, subject, content, reg_date, bHit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		
		return dto;
	}
	
	// 조회수 늘리기
	public void upHit(int idx) {
		String sql = "UPDATE bbs SET bHit=bHit+1 WHERE idx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수정
	public int update(BoardDTO dto) {
		int success = 0;
		String sql = "UPDATE bbs SET subject=?, content=? WHERE idx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getIdx());
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}

	// 글쓰기
	public int write(String loginId, String subject, String content) {
		int success = 0;
		String sql = "INSERT INTO bbs(idx, user_name, subject, content) VALUES(bbs_seq.NEXTVAL, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			success = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return success;
	}

}
