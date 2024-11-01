package com.ict.edu01.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KaKaoMapController {
	@GetMapping("/kakao_map01")
	public ModelAndView goKakaoMap01() {
		return new ModelAndView("map/kakaomap01");
	}
	@GetMapping("/kakao_map02")
	public ModelAndView goKakaoMap02() {
		return new ModelAndView("map/kakaomap02");
	}
	@GetMapping("/kakao_map03")
	public ModelAndView goKakaoMap03() {
		return new ModelAndView("map/kakaomap03");
	}
	@GetMapping("/kakao_map04")
	public ModelAndView goKakaoMap04() {
		return new ModelAndView("map/kakaomap04");
	}
	@GetMapping("/index")
	public ModelAndView goIndex() {
		return new ModelAndView("index");
	}
}
