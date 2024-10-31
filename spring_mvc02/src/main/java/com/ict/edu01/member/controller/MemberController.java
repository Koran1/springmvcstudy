package com.ict.edu01.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.member.dao.MemberService;
import com.ict.edu01.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/member_login")
	public ModelAndView authLogin(MemberVO memvo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			MemberVO memvo_true = memberService.getMemberInfo(memvo.getM_id());
			
			// id check
			if(memvo_true != null) {
				
				// pw check
				if(passwordEncoder.matches(memvo.getM_pw(), memvo_true.getM_pw())) {
					
					session.setAttribute("logchk", "ok");
					session.setAttribute("mvo", memvo_true);
					// admin check
					if(memvo_true.getM_id().equals("admin")) {
						session.setAttribute("admin", "ok");
					}
					mv.setViewName("shop/product_list");
				}else {
					session.setAttribute("logchk", "fail");
					mv.setViewName("sns/loginform");
				}
			}else {
				session.setAttribute("logchk", "fail");
				mv.setViewName("sns/loginform");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("sns/loginform");
		}
		
		return mv;
	}
	
	@GetMapping("/member_logout")
	public ModelAndView memberLogout(HttpSession session) {
		// 1. 세션 초기화 (전체 삭제)
		session.invalidate();
		
		// 2. 세션 필요정보만 삭제
		/*
		 * session.removeAttribute("logchk"); session.removeAttribute("mvo");
		 * session.removeAttribute("admin");
		 */
		
		return new ModelAndView("redirect:/shop/list");
	}
	
	
}
