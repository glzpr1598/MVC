package com.kh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.kh.dto.BoardDTO;

/*
 CREATE TABLE photo(
    fileIdx NUMBER(6,0) PRIMARY KEY,
    idx NUMBER(6,0) NOT NULL,
    oriFileName VARCHAR2(50),
    newFileName VARCHAR2(400),
    CONSTRAINT fk_photo_idx FOREIGN KEY(idx) 
    REFERENCES bbs(idx)ON DELETE CASCADE
); 

CREATE SEQUENCE photo_seq;
 */

public class PhotoDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public PhotoDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//리스트 불러오기
	public ArrayList<BoardDTO> list() {		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();		
		try {
			//쿼리 와 ps 준비
			String sql = "SELECT * FROM bbs ORDER BY idx DESC";
			ps = conn.prepareStatement(sql);//쿼리 실행			
			rs = ps.executeQuery();
			while(rs.next()) {//rs 에서 값 가져와 dto 담기
				BoardDTO dto = new BoardDTO();				
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
				list.add(dto);//dto 를 list 에 담기
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();//자원 반납
		}	
		return list;
	}

	private void resClose() {
		try {
			if(rs != null) {
				rs.close();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	//상세보기
	public BoardDTO detail(String idx) {
		BoardDTO dto = null;
		String sql="SELECT * FROM bbs WHERE idx = ?";
		try {
			ps  = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(idx));
			rs = ps.executeQuery();
			if(rs.next()) {
				upHit(idx);
				dto = new BoardDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setContent(rs.getString("content"));
				dto.setSubject(rs.getString("subject"));
				dto.setReg_date(rs.getDate("reg_date"));
				dto.setbHit(rs.getInt("bHit"));
			}
			//파일명 추출
			String newFileName = fileNameCall(dto.getIdx());
			if(newFileName != null) {
				dto.setNewFileName(newFileName);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return dto;
	}

	//게시글에 해당하는 파일명 추출
	public String fileNameCall(int idx) {		
		String sql="SELECT newFileName From photo WHERE idx = ?";
		String fileName = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			fileName = rs.next() ? rs.getString("newFileName") : null;			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return fileName;
	}

	//조회수 올리기
	private void upHit(String idx) {
		String sql="UPDATE bbs SET bHit = bHit+1 WHERE idx = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(idx));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	//글쓰기
	public int write(BoardDTO dto) {
		String sql="INSERT INTO bbs(idx,user_name,subject,content) "
				+"VALUES(bbs_seq.NEXTVAL,?,?,?)";
		long fk = 0;
		try {
			//ojdbc 8  버전 이상에서 가능
			//2번째 인자 값은 값을 넣고 반환 해 줄 컬럼
			//new String[]{"반환받을 컬럼명"} 또는 new int[]{반환받을 컬럼번호}
			ps = conn.prepareStatement(sql, new String[] {"idx"});//bbs 테이블에 데이터 추가
			ps.setString(1, dto.getUser_name());
			ps.setString(2, dto.getSubject());
			ps.setString(3, dto.getContent());			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();//이 과정으로 원하는 컬럼을 받아 올 수 있다.
			String fileName = dto.getNewFileName();
			System.out.println("fileName : "+fileName);
			if(rs.next()) {
				fk = rs.getLong(1);//넣은 값의 idx 받아오기
				if(fileName!=null) {
					//idx 를 이용해서 photo 테이블에 데이터 넣기
					sql = "INSERT INTO photo (fileIdx,idx,newFileName) "
							+"VALUES(photo_seq.NEXTVAL,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setLong(1, fk);
					ps.setString(2, fileName);
					ps.executeUpdate();
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return (int) fk;//idx 값 반환
	}

	//글 삭제 메서드
	public int del(String id) {
		int success = 0;
		String sql="DELETE FROM bbs WHERE idx = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			success = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			resClose();
		}
		return success;
	}

	//수정
	public int update(BoardDTO dto) {
		String sql="UPDATE bbs SET subject=?, content=?, user_name=?"
				+" WHERE idx=?";
		int success = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getSubject());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getUser_name());
			ps.setInt(4, dto.getIdx());
			success = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}				
		return success;
	}

	public void fileNameUpdate(int idx, String newFileName, String oldFileName) {
		String sql="";		
		try {
			if(oldFileName != null) {//기존에 올린 파일이 있는 경우(UPDATE)
				sql="UPDATE photo SET newFileName = ? WHERE idx = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1,newFileName);
				ps.setInt(2, idx);
			}else {//기존 파일이 없는 경우(INSERT)
				sql="INSERT INTO photo VALUES(photo_seq.NEXTVAL,?,?,?)";
				ps = conn.prepareStatement(sql);			
				ps.setInt(1,idx);
				ps.setString(2, "no file");
				ps.setString(3, newFileName);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}		
	}
}







