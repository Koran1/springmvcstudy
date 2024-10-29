package com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 컨트롤러임을 Annotation으로 구현(implement)해야한다

public class Start1Controller implements Controller {
	// 실행하는 메서드
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// servlet-context.xml 로 되돌아 갔다가 ViewResolver 에 들어가는 이름
		// = 결과를 보여줄 jsp 이름 (.jsp 붙이면 안됨)
		// 방법1)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result1");
		
		// 방법2)
		ModelAndView mv2 = new ModelAndView("result1");
		
		// Spring에서 일처리 하는 패턴 순서 (전자정부 프레임워크 와 동일한 패턴) 
		// 일처리 =>	서비스(interface) => 서비스임플(클래스) => 	DAO(interface) => DAO 임플 => DB
		//			비즈니스 레이어								Repository layer (=Mapper)
		
		
		// JSP 에서 표현하기 위해서 값을 저장
		// 1. request 이용 (JSP MVC 와 동일) = 비추천
		request.setAttribute("name", "홍길동");
		// 2. ModelAndView 이용 (default) = 추천
		mv.addObject("age", 27);
		// 3. session (로그인 처리할 때만 사용) 
		request.getSession().setAttribute("phone", "010-7777-9999");
		
		
		
		
		
		return mv;
	}
}
