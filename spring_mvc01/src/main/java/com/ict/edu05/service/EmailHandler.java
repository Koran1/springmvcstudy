package com.ict.edu05.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

// Service 에서 호출해서 사용할 클래스

public class EmailHandler {
	
	private JavaMailSender javaMailSender;
	private MimeMessage msg;
	private MimeMessageHelper msgHelper;
			
	public EmailHandler(JavaMailSender javaMailSender) throws Exception {
		this.javaMailSender = javaMailSender;
		msg = this.javaMailSender.createMimeMessage();
		msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
		// msgHelper = new MimeMessageHelper(msg, multipart 지원 여부, "UTF-8");
		// msgHelper = new MimeMessageHelper(msg, "UTF-8");	=> 단순 텍스트 메시지만 지원
	}

	// 제목
	public void setSubject(String subject) throws Exception{
		msgHelper.setSubject(subject);
	}
	
	// 내용
	public void setText(String text) throws Exception{
		msgHelper.setText(text, true);
	}
	
	// 보내는 사람의 이메일과 제목
	public void setFrom(String email, String name) throws Exception{
		msgHelper.setFrom(email, name);
	}
	
	// 받는 이메일
	public void setTo(String email) throws Exception{
		msgHelper.setTo(email);
	}
	
	// 보내기
	public void send() {
		javaMailSender.send(msg);
	}
	
	
}
