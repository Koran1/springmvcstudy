package com.ict.edu04.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.edu04.service.AjaxMembersService;
import com.ict.edu04.vo.MembersVO;

@RestController
public class AjaxDBController {
	
	@Autowired
	private AjaxMembersService ajaxMembersService;
	
	@RequestMapping(value="/ajax_db_list", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public String getAjaxList() {
		List<MembersVO> list = ajaxMembersService.getMembersList();
		
		if(list != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<members>");
			for (MembersVO k : list) {
				sb.append("<member>");
				sb.append("<m_idx>"	+k.getM_idx()+	"</m_idx>");
				sb.append("<m_id>"	+k.getM_id()+	"</m_id>");
				sb.append("<m_pw>"	+k.getM_pw()+	"</m_pw>");
				sb.append("<m_name>"+k.getM_name()+	"</m_name>");
				sb.append("<m_age>"+k.getM_age()+	"</m_age>");
				sb.append("<m_reg>"	+k.getM_reg()+	"</m_reg>");
				sb.append("</member>");
			}
			
			sb.append("</members>");
			return sb.toString();
		}
		return "fail";
	}
	
	@RequestMapping(value="/ajax_db_list2", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAjaxList2() {
		List<MembersVO> list = ajaxMembersService.getMembersList();
		if(list != null) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		}
		return "fail";
	}
	
	@RequestMapping(value="/ajax_db_list3", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String getAjaxList3() {
		List<MembersVO> list = ajaxMembersService.getMembersList();
		
		if(list != null) {
			// CSV 만들기
			StringBuffer sb = new StringBuffer();
			// 헤더 추가 (컬럼명) => 생략 가능
			sb.append("m_idx,m_id,m_pw,m_name,m_age,m_reg\n");
			
			for (MembersVO k : list) {
				sb.append(String.format("%s,%s,%s,%s,%s,%s\n",
						k.getM_idx(), k.getM_id(), k.getM_pw(), k.getM_name(),k.getM_age(),k.getM_reg()));
			}
			return sb.toString();
		}
		return "fail";
	}
	
	@RequestMapping(value="/ajax_db_chkid", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxIdChk(HttpServletRequest request) {
		String result = ajaxMembersService.getMembersIdChk(request.getParameter("m_id"));
		return result;
	}
	
	@RequestMapping(value="/ajax_member_join", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxMemberJoin(MembersVO mvo) {
		String result = ajaxMembersService.getMembersInsert(mvo);
		return result;
	}
	
	@RequestMapping(value="/ajax_member_delete", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxMemberDelete(@RequestParam("m_idx") String m_idx) {
		String result = ajaxMembersService.getMembersDelete(m_idx);
		return result;
	}
	
	
}
