package com.ict.edu05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	// Controller 에서 호출할 메서드 생성
	public void sendEmail(String randomNumber, String toMail) {
		try {
			EmailHandler sendMail = new EmailHandler(javaMailSender);
			
			sendMail.setSubject("[ICT EDU 인증 메일 테스트]");
			sendMail.setText("<table><tbody>"+
					"<tr><td><h2>ICT EDU 메일 인증 테스트용</h2></td></tr>"+
					"<tr><td><h3>ICT EDU 에요 </h3></td></tr>"+
					"<tr><td><font size='8px'> 인증 번호 안내 </font></td></tr>"+
					"<tr><td><font size='16px'>"+randomNumber+" </font></td></tr>"+
					"</tbody></table>"
					);
			sendMail.setFrom("ictedu@gmail.com", "ictedu admin");
			sendMail.setTo(toMail);
			sendMail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
