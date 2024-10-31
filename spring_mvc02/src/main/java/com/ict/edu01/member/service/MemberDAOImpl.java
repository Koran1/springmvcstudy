package com.ict.edu01.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu01.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{ 
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public MemberVO getMemberInfo(String m_id) throws Exception{
		return sqlSessionTemplate.selectOne("shop.member_info", m_id);
	}
	
}
