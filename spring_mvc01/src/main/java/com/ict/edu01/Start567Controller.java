package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start567Controller {
	@GetMapping("/start5")
	public ModelAndView Start5() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("day01/result567");
		mv.addObject("origin", "start5");
		mv.addObject("msg", "Hello5");
		return mv;
	}
	@GetMapping("/start6")
	public ModelAndView Start6(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("day01/result567");
		mv.addObject("origin", "start6");
		mv.addObject("msg", "Hi6");
		
		return mv;
	}
	
	@GetMapping("/start7")
	public ModelAndView Start7(HttpServletRequest request) {
		request.setAttribute("msg", "안녕하세요7");
		return new ModelAndView("day01/result567");
	}
	
	@GetMapping("/start8")
	public ModelAndView Start8(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("day01/result8");
		mv.addObject("username", request.getParameter("username"));
		mv.addObject("userage", request.getParameter("userage"));
		return mv;
	}
	
}
