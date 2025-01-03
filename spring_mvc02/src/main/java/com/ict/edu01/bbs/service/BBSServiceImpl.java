package com.ict.edu01.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu01.bbs.dao.BBSDAO;
import com.ict.edu01.bbs.vo.BBSVO;
import com.ict.edu01.bbs.vo.CommentVO;

@Service
public class BBSServiceImpl implements BBSService{

	@Autowired
	private BBSDAO bbsdao;
	
	@Override
	public List<BBSVO> getBBSList() {
		return bbsdao.getBBSList();
	}

	@Override
	public int getBBSInsert(BBSVO bvo) {
		return bbsdao.getBBSInsert(bvo);
	}

	@Override
	public BBSVO getBBSDetail(String b_idx) {
		return bbsdao.getBBSDetail(b_idx);
	}

	@Override
	public int getBBSDelete(String b_idx) {
		return bbsdao.getBBSDelete(b_idx);
	}

	@Override
	public int getBBSUpdate(BBSVO bvo) {
		return bbsdao.getBBSUpdate(bvo);
	}

	@Override
	public int getHitUpdate(String b_idx) {
		return bbsdao.getHitUpdate(b_idx);
	}

	@Override
	public int getTotalCount() {
		return bbsdao.getTotalCount();
	}

	@Override
	public List<BBSVO> getBBSList(int offset, int limit) {
		return bbsdao.getBBSList(offset, limit);
	}

	@Override
	public List<CommentVO> getCommentList(String b_idx) {
		return bbsdao.getCommentList(b_idx);
	}

	@Override
	public int getCommentInsert(CommentVO cvo) {
		return bbsdao.getCommentInsert(cvo);
	}

	@Override
	public int getCommentDelete(String c_idx) {
		return bbsdao.getCommentDelete(c_idx);
	}

}
