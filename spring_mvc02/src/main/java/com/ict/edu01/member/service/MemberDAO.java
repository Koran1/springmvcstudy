package com.ict.edu01.member.service;

import com.ict.edu01.member.vo.MemberVO;

public interface MemberDAO {
	public MemberVO getMemberInfo(String m_id) throws Exception;
}
