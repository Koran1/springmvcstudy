package com.ict.edu04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {
	
	@GetMapping("/go_ajax_xml")
	public ModelAndView goAjaxXML() {
		return new ModelAndView("day04/ajax_result_xml");
	}
	@GetMapping("/go_ajax_json")
	public ModelAndView goAjaxJSON() {
		return new ModelAndView("day04/ajax_result_json");
	}
	@GetMapping("/go_ajax_DB")
	public ModelAndView goAjaxDB() {
		return new ModelAndView("day04/ajax_result_db");
	}
}
