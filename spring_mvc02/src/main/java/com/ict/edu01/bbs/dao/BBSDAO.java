package com.ict.edu01.bbs.dao;

import java.util.List;

import com.ict.edu01.bbs.vo.BBSVO;
import com.ict.edu01.bbs.vo.CommentVO;

public interface BBSDAO {
	 // 리스트
    public List<BBSVO> getBBSList();
    
    // 삽입
    public int getBBSInsert(BBSVO bvo);
    
    // 상세보기
    public BBSVO getBBSDetail(String b_idx);
    
    // 원글 삭제
    public int getBBSDelete(String b_idx);
    
    // 원글 수정 
    public int getBBSUpdate(BBSVO bvo);
    
    // 조회수 업데이트
    public int getHitUpdate(String b_idx);
    // 페이징 처리 - 전체 게시물의 수
    public int getTotalCount();
    
    // 페이징 처리을 위한 리스트 
    public List<BBSVO> getBBSList(int offset, int limit);
    
    // 댓글 가져오기
    public List<CommentVO> getCommentList(String b_idx);
    // 댓글 삽입
    public int getCommentInsert(CommentVO cvo);
    // 댓글 삭제
    public int getCommentDelete(String c_idx);   
}
