package com.ict.edu01.guestbook.service;

import java.util.List;

import com.ict.edu01.guestbook.vo.GuestbookVO;

public interface GuestbookService {
	public List<GuestbookVO> getGuestbookList();
	public int insertGuestbook(GuestbookVO gbvo);
	public GuestbookVO getGuestbookOneList(String gb2_idx);
 	public int updateGuestbook(GuestbookVO gbvo);
	public int deleteGuestbook(String gb2_idx);
}
