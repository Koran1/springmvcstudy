package com.ict.edu01.guestbook.dao;

import java.util.List;

import com.ict.edu01.guestbook.vo.GuestbookVO;

public interface GuestbookDAO {
	public List<GuestbookVO> getGuestbookList();
	public int insertGuestbook(GuestbookVO gbvo);
	public GuestbookVO getGuestbookOneList(String gb2_idx);
 	public int updateGuestbook(GuestbookVO gbvo);
	public int deleteGuestbook(String gb2_idx);
}
