package com.ict.edu02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu02.service.CalcService;
import com.ict.edu02.service.MemberService;
import com.ict.edu02.vo.Calc_VO;
import com.ict.edu02.vo.MembersVO;

@Controller
public class MyController3 {
	
	@Autowired						// 다른 곳에서 만들어진 클래스가 연결되게끔 해줌 (단, 클래스 이름이 똑같아야한다)
	private CalcService calcService;
	// @Inject						// java 에서 만든 autowired
	
	
	/*
	 * @PostMapping("/calc2") public ModelAndView calc2Exec(Calc_VO cvo) { //
	 * Calc_VO cvo 의 파라미터들이 알아서 넣어진다 // 변수와 파라미터 이름이 반드시 같아야 한다! ModelAndView mv =
	 * new ModelAndView("day02/result1");
	 * 
	 * // 파라미터가 저장되어 있는지 확인 //System.out.println(cvo.getS1());
	 * 
	 * // 일처리는 service 에서 한다! mv.addObject("result", calcService.getCalc(cvo));
	 * mv.addObject("cvo", cvo);
	 * 
	 * return mv; }
	 */
	
	@PostMapping("/calc2")
	public ModelAndView calc2Exec(@ModelAttribute("cvo") Calc_VO cvo ) {
		ModelAndView mv = new ModelAndView("day02/result1");
		mv.addObject("result", calcService.getCalc(cvo));
		
		return mv;
	}
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member_list")
	public ModelAndView memberList() {
		ModelAndView mv = new ModelAndView("day02/member_list");
		List<MembersVO> list = memberService.getMemberList();
		mv.addObject("list", list); 
		return mv;
	}
}
