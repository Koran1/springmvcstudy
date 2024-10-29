package com.ict.edu06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu06.service.LoginService;
import com.ict.edu06.vo.LoginVO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/login_form")
	public ModelAndView goLoginForm() {
		return new ModelAndView("day06/login_loginform");
	}
	
	@GetMapping("/join_form")
	public ModelAndView goJoinForm() {
		return new ModelAndView("day06/login_joinform");
	}
	
	@PostMapping("/login_join_ok")
	public ModelAndView getLoginJoinOK(LoginVO lvo) {
		ModelAndView mv = new ModelAndView("day06/login_loginform");
		try {
			
			// PW 암호화 후 저장
			String m_pw = passwordEncoder.encode(lvo.getM_pw());
			lvo.setM_pw(m_pw);
			
			int result = loginService.LoginJoin(lvo);
			if(result > 0) {
				mv.addObject("result", "1");
			}
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("result", "0");
			return mv;
		}
		
	}
	@PostMapping("/login_login_ok")
	public ModelAndView getLoginOK(LoginVO lvo) {
		ModelAndView mv = new ModelAndView();
		try {
			// 1. 아이디 가지고 DB 갔다오기
			LoginVO loginVO = loginService.LoginChk(lvo);
			if (loginVO != null) {
				// 2. 사용자가 입력한 암호 추출
				String userM_pw = lvo.getM_pw();
				
				// 3. 두 암호를 비교해서 같으면 성공, 다르면 실패
				if(passwordEncoder.matches(userM_pw, loginVO.getM_pw())) {
					mv.addObject("loginchk", "1");
					mv.setViewName("index");
				} else {
					mv.addObject("loginchk", "0");
					mv.setViewName("day06/login_loginform");
				}
				
			}else { 
				mv.addObject("loginchk", "0");
				mv.setViewName("day06/login_loginform");
			}
			return mv;
			
		} catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
		
	}
	
}
