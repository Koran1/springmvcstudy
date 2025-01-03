package com.ict.edu01.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 결과가 true 면 controller 이동
		// 결과가 false면 특정 jsp 로 이동
		
		// ex) 로그인 체크를 해서 만약 로그인 안된 상태면 value 에 false 값 저장
		// => 로그인 하면 세션에 로그인 성공 정보 or 사용자 정보를 담자
		
		Object obj = request.getSession(true).getAttribute("loginchk");
		// getSession(true) = session이 삭제된 상태라면 새로운 session 생성
		// 삭제가 안된 상태라면 기존 session 정보 넘겨준다
		
		if(obj == null) {	// 로그인 안 된 상태
			// response.sendRedirect("/WEB-INF/views/sns/loginform.jsp");
			String script = "<script> alert('로그인이 필요합니다'); location.href = '/sns_login'; </script>";
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(script);
			return false;
		}
		
		return true;
	}
	
	/* @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	} */
}
