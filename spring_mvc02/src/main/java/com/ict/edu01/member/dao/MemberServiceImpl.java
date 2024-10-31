package com.ict.edu01.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu01.member.service.MemberDAO;
import com.ict.edu01.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberVO getMemberInfo(String m_id) throws Exception {
		return memberDAO.getMemberInfo(m_id);
	}
	
	
}
