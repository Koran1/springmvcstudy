package com.ict.edu05.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu05.service.EmailService;

@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/email")
	public ModelAndView emailForm() {
		return new ModelAndView("day05/email_form");
	}
	
	@PostMapping("/email_send")
	public ModelAndView sendEmail(
			@RequestParam String email,
			HttpServletRequest request
			) {
		try {
			ModelAndView mv = new ModelAndView("day05/email_form");
			// 임시번호 6자리 만들기
			Random random = new Random();
			String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
			
			if(randomNumber.length() < 6) {
				int subtract = 6 - randomNumber.length();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < subtract; i++) {
					sb.append("0");
				}
				sb.append(randomNumber);
				randomNumber = sb.toString();
			}
			// 임시번호 확인
			// System.out.println(randomNumber);
			
			// 해당 임시번호를 DB에 저장하거나 세션에 저장한다
			request.getSession().setAttribute("email_chknum", randomNumber);
			
			// 사용자에게 메일 보내기
			emailService.sendEmail(randomNumber, email);
			
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/email_send_ok")
	public ModelAndView sendEmailOK(
			@RequestParam("authNumber") String authNumber,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("day05/email_chkform");
		String sessionNum = (String) request.getSession().getAttribute("email_chknum");
		if(authNumber.equals(sessionNum)) {
			mv.addObject("chk", 1);
		}
		return mv;
	}
}