package com.ict.edu01.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu01.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("board.count");
	}

	@Override
	public List<BoardVO> getBoardList(int offset, int limit) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("offset", offset);
		map.put("limit", limit);
		return sqlSessionTemplate.selectList("board.getlist", map);
	}

	@Override
	public int getBoardInsert(BoardVO bovo) {
		return sqlSessionTemplate.insert("board.insert", bovo);
	}

	@Override
	public int getBoardHit(String bo_idx) {
		return sqlSessionTemplate.update("board.hitupdate", bo_idx);
	}

	@Override
	public BoardVO getBoardDetail(String bo_idx) {
		return sqlSessionTemplate.selectOne("board.detail", bo_idx);
	}

	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return sqlSessionTemplate.update("board.ans_levupdate", map);
	}

	@Override
	public int getAnsInsert(BoardVO bovo) {
		return sqlSessionTemplate.insert("board.ans_insert", bovo);
	}

	@Override
	public int getBoardDelete(String idx) {
		return sqlSessionTemplate.update("board.delete", idx);
	}

	@Override
	public int getBoardUpdate(BoardVO bovo) {
		return sqlSessionTemplate.update("board.update", bovo);
	}
	
}
