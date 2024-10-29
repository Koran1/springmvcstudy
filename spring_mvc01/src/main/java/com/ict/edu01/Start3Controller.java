package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Annotation 으로 @Controller 를 사용하면 Spring 에서 자동으로 Controller 로 인식한다

@Controller
public class Start3Controller {
	// URL 매핑
	// 1. 옛날 방식
	// @RequestMapping(value = "URL", method = RequestMethod.GET);
	// @RequestMapping(value = "URL", method = RequestMethod.POST);
	
	// 2. 요즘 방식
	// @RequestMapping("URL");			// 이 경우는 get, post 두 경우 모두 다 받아줌
	// @GetMapping("URL 매핑 주소");
	// @PostMapping("URL 매핑 주소");
	
	// 해당 메서드는 URL 매핑이 와야 실행된다
	// exec(인자) : 인자는 필요할 때만 사용하면 된다
	@GetMapping("/start3")
	// @PostMapping("/start3")
	public ModelAndView exec(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("day01/result3");
		mv.addObject("city","서울");
		return mv;
	}
}
