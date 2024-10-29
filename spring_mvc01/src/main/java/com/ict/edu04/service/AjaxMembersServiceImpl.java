package com.ict.edu04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu04.dao.AjaxMembersDAO;
import com.ict.edu04.vo.MembersVO;

@Service
public class AjaxMembersServiceImpl implements AjaxMembersService{

	@Autowired
	private AjaxMembersDAO ajaxMembersDAO;
	
	@Override
	public List<MembersVO> getMembersList() {
		return ajaxMembersDAO.getMembersList();
	}

	@Override
	public String getMembersIdChk(String m_id) {
		return ajaxMembersDAO.getMembersIdChk(m_id);
	}
	
	@Override
	public MembersVO getMembersDetail(String m_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMembersInsert(MembersVO mvo) {
		return ajaxMembersDAO.getMembersInsert(mvo);
	}

	@Override
	public String getMembersDelete(String m_idx) {
		return ajaxMembersDAO.getMembersDelete(m_idx);
	}

	@Override
	public String getMembersUpdate(MembersVO mvo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
