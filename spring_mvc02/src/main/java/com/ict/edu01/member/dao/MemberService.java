package com.ict.edu01.member.dao;

import com.ict.edu01.member.vo.MemberVO;

public interface MemberService {
	public MemberVO getMemberInfo(String m_id) throws Exception;
}
