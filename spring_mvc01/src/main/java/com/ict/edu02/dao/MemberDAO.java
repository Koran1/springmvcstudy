package com.ict.edu02.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.ict.edu02.vo.MembersVO;

public class MemberDAO {
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<MembersVO> getMemberList() {
		List<MembersVO> list = null;
		try {
			list = sqlSessionTemplate.selectList("members.getList");
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	
	
}
