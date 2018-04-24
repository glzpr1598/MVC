package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bbs.dto.BbsDTO;

public class BbsDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public BbsDAO() {
		try {
			// DB 연결
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 자원 반납
	public void resClose() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public Boolean login(String id, String pw) {
		boolean result = false;
		//ID, PW 가 맞는 지 확인하기위해 쿼리문 준비
		String sql = "SELECT id FROM member WHERE id =? AND pw=? ";
		System.out.println("DAO2");
		try {
			//쿼리 실행하기 위해 PrepareStatement 준비
			ps =conn.prepareStatement(sql);
			//실행 하기 전에 ? 대응
			ps.setString(1, id);
			ps.setString(2, pw);
			System.out.println(id + " "+ pw);
			//쿼리 실행
			rs =ps.executeQuery();
			result =rs.next();
			System.out.println(rs.next());
			System.out.println(result);
			System.out.println("DAO3");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}finally {
			resClose();
		}
		return result;
	}
	
	/*DB에서 리스트 가져오는 메서드*/
	public ArrayList<BbsDTO> list() {
		//DB에서 추출 한 값을 받는 변수 선언 . 
		ArrayList<BbsDTO> list = new ArrayList<BbsDTO>();
		
		//접속 할 쿼리 준비
		String sql = "SELECT * FROM bbs ORDER BY idx";
		try {
			//쿼리 실행 시키기 위해 PrepareStatement 불러오기
			ps =conn.prepareStatement(sql);
			rs =ps.executeQuery();
			while(rs.next()) {
				//DB를 통해 얻은 변수를 DTO에 담기 
				//->그러기 위해서는 BbsDTO 객체화시킴
				BbsDTO dto = new BbsDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setbHit(rs.getString("BHIT"));
				dto.setREG_DATE(rs.getString("REG_DATE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			resClose();
		}
		return list;
	}
	
	// 수정
	public int update(BbsDTO dto) {
		// 쿼리문 준비
		String sql = "UPDATE bbs SET subject=?, content=? WHERE idx=?";
		int success = 0;
		
		try {
			// 쿼리 실행
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getSubject());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getIdx());
			success = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		return success;
	}
	
	public int write(BbsDTO dto) {
		int result=0;
		System.out.println("test");
		try {
			Context ctx=new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn =ds.getConnection();
			//쿼리준비
			String sql="INSERT INTO bbs (idx, user_name, subject, content, bHit) "
					+"VALUES(bbs_seq.NEXTVAL,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getUser_name());
			//ps.setString(1, "abc");
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());
			ps.setInt(4, 0);
			System.out.println(dto.getSubject());
			System.out.println(dto.getContent());
			result=ps.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			result=0;
		}finally {
			resClose();
		}
		return result;
	}
	
	public int bbsDel(int idx) {
		int success = 0;
		String sql = "DELETE FROM bbs WHERE idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			resClose();
		}
		return success;	
	}

	public BbsDTO detail(int idx) {
		BbsDTO dto = null;
		
		//쿼리 준비
		String sql = "SELECT * FROM bbs WHERE idx=?";
		try {
			//PreparedStatement 준비
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			//쿼리 실행
			rs = ps.executeQuery();
			
			//rs 값 추출
			while(rs.next()) {
				dto = new BbsDTO();
				
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setbHit(rs.getString("bHit"));
				dto.setREG_DATE(rs.getString("REG_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return dto;
		}finally {
			resClose();
		}
		return dto;
	}

}
