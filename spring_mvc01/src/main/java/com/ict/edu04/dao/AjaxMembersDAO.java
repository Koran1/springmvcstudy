package com.ict.edu04.dao;

import java.util.List;

import com.ict.edu04.vo.MembersVO;

public interface AjaxMembersDAO {
	public List<MembersVO> getMembersList();
	public String getMembersIdChk(String m_id);
	public MembersVO getMembersDetail(String m_idx);
	public String getMembersInsert(MembersVO mvo);
	public String getMembersDelete(String m_idx);
	public String getMembersUpdate(MembersVO mvo);
}
