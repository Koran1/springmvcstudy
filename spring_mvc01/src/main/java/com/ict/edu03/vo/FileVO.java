package com.ict.edu03.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	// fname 은 DB 에 파일 이름 저장용
	private String name, fname;
	// 파라미터 = f_name (사실상 파일 그 자체)
	private MultipartFile f_name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public MultipartFile getF_name() {
		return f_name;
	}
	public void setF_name(MultipartFile f_name) {
		this.f_name = f_name;
	}
	
	
}
