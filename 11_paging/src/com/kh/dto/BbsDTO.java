package com.kh.dto;

import java.sql.Date;

public class BbsDTO {

	private int idx;
	private String user_name;
	private String subject;
	private String content;
	private Date reg_date;
	private int bHit;
	
	public BbsDTO() {
		
	}
	
	public BbsDTO(int idx, String user_name, String subject, String content, Date reg_date, int bHit) {
		super();
		this.idx = idx;
		this.user_name = user_name;
		this.subject = subject;
		this.content = content;
		this.reg_date = reg_date;
		this.bHit = bHit;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

}
