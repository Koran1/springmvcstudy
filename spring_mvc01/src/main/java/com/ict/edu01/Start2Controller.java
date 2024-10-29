package com.ict.edu01;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start2Controller implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2");
		
		// data가 DB를 갔다 온 상태라고 가정
		String[] dogName = {"땅콩이", "진돌이", "말복이", "하오"};
		mv.addObject("dogName", dogName);
		
		ArrayList<String> catName = new ArrayList<String>();
		catName.add("뽀미");
		catName.add("꼬미");
		catName.add("나비");
		catName.add("초코");
		
		mv.addObject("catName", catName);
		mv.addObject("carol", "");
		return mv;
	}
}
