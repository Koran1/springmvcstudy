package com.ict.edu01.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu01.bbs.vo.BBSVO;
import com.ict.edu01.bbs.vo.CommentVO;

@Repository
public class BBSDAOImpl implements BBSDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<BBSVO> getBBSList() {
		return sqlSessionTemplate.selectList("bbs.list");
	}

	@Override
	public int getBBSInsert(BBSVO bvo) {
		return sqlSessionTemplate.insert("bbs.insert", bvo);
	}

	@Override
	public BBSVO getBBSDetail(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.detail", b_idx);
	}

	@Override
	public int getBBSDelete(String b_idx) {
		return sqlSessionTemplate.delete("bbs.delete", b_idx);
	}

	@Override
	public int getBBSUpdate(BBSVO bvo) {
		return sqlSessionTemplate.update("bbs.update", bvo);
	}

	@Override
	public int getHitUpdate(String b_idx) {
		return sqlSessionTemplate.update("bbs.hitup", b_idx);
	}

	@Override
	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("bbs.count");
	}

	@Override
	public List<BBSVO> getBBSList(int offset, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("bbs.pagelist", map);
	}

	@Override
	public List<CommentVO> getCommentList(String b_idx) {
		return sqlSessionTemplate.selectList("comment.clist", b_idx);
	}

	@Override
	public int getCommentInsert(CommentVO cvo) {
		return sqlSessionTemplate.insert("comment.insert", cvo);
	}

	@Override
	public int getCommentDelete(String c_idx) {
		return sqlSessionTemplate.delete("comment.delete", c_idx);
	}

}
