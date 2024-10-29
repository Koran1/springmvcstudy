package com.ict.edu05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu05.service.EmpService;
import com.ict.edu05.vo.DeptVO;
import com.ict.edu05.vo.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/emp")
	public ModelAndView emp_form() {
		return new ModelAndView("day05/emp_form");
	}
	
	@PostMapping("/emp_getlist")
	public ModelAndView emp_list() {
		ModelAndView mv = new ModelAndView("day05/emp_list");
		try {
			List<EmpVO> list = empService.getList();
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			// return new ModelAndView("error");		// Error 페이지 로 리턴
			return null;			// 또는 null 로 리턴
		}
	}
	
	@PostMapping("/emp_search")
	public ModelAndView emp_search(@RequestParam("deptno") String deptno) {
		ModelAndView mv = new ModelAndView("day05/emp_search");
		try {
			List<EmpVO> list = empService.getSearch(deptno);
			mv.addObject("list", list);
			DeptVO dept = empService.getDeptSearch(deptno);
			mv.addObject("dept", dept);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 파라미터에 vo가 존재하지 않는 경우 
	// 1. vo 에 파라미터를 추가한다
	
	/* @PostMapping("/emp_dynamic_search")
	public ModelAndView emp_dynamic_search(EmpVO empvo) {
		ModelAndView mv = new ModelAndView("day05/emp_dynamic");
		try {
			List<EmpVO> list = empService.getSearch(empvo);
			mv.addObject("list", list);
			mv.addObject("idx", empvo.getIdx());
			mv.addObject("keyword", empvo.getKeyword());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} */
	
	// 2. 별도로 받아서 나중에 Map 으로 처리한다
	@PostMapping("/emp_dynamic_search")
	public ModelAndView emp_dynamic_search(
			@ModelAttribute("idx") String idx,
			@ModelAttribute("keyword") String keyword		
			// 받은 idx와 keyword를 다음 jsp 로 그대로 넘기기 위해서
		) {
		try {
			ModelAndView mv = new ModelAndView("day05/emp_dynamic");
			List<EmpVO> list = empService.getSearch(idx, keyword);
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
