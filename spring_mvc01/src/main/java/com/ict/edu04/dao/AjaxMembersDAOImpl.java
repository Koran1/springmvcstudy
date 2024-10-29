package com.ict.edu04.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu04.vo.MembersVO;

@Repository
public class AjaxMembersDAOImpl implements AjaxMembersDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<MembersVO> getMembersList() {
		return sqlSessionTemplate.selectList("ajaxmembers.getMembersList");
	}

	@Override
	public String getMembersIdChk(String m_id) {
		System.out.println(m_id);
		int result = sqlSessionTemplate.selectOne("ajaxmembers.getMembersIdChk", m_id);
		System.out.println(result);
		return result+"";
		// return String.valueOf(result);
	}
	
	@Override
	public MembersVO getMembersDetail(String m_idx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getMembersInsert(MembersVO mvo) {
		int result = sqlSessionTemplate.insert("ajaxmembers.getMemberInsert", mvo);
		return String.valueOf(result);
	}

	@Override
	public String getMembersDelete(String m_idx) {
		int result = sqlSessionTemplate.delete("ajaxmembers.getMemberDelete", m_idx);
		return result+"";
	}

	@Override
	public String getMembersUpdate(MembersVO mvo) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
