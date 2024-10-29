package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController2 {
	
	@PostMapping("/calc")
	public ModelAndView calcExec(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("day01/result9");
		String s1 = request.getParameter("s1");
		String s2 = request.getParameter("s2");
		String op = request.getParameter("op");
		String[] hobby = request.getParameterValues("hobby");
		String cPage = request.getParameter("cPage");
		
		// 일처리
		int su1 = Integer.parseInt(s1);
		int su2 = Integer.parseInt(s2);
		int result = 0;
		switch (op) {
		case "+": 	result = su1+su2;	break;
		case "-": 	result = su1-su2;	break;
		case "*": 	result = su1*su2;	break;
		case "/": 	result = su1/su2;	break;
		}
		
		// 결과 저장
		mv.addObject("su1", su1);
		mv.addObject("su2", su2);
		mv.addObject("op", op);
		mv.addObject("result", result);
		mv.addObject("hobby", hobby);
		mv.addObject("cPage", cPage);
		
		return mv;
	}
}
