package com.ict.edu01.guestbook.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ict.edu01.guestbook.dao.GuestbookDAO;
import com.ict.edu01.guestbook.vo.GuestbookVO;

@Service
public class GuestbookServiceImpl implements GuestbookService {
	
	@Autowired
	private GuestbookDAO guestbookDAO;

	@Override
	public List<GuestbookVO> getGuestbookList() {
		return guestbookDAO.getGuestbookList();
	}

	@Override
	public int insertGuestbook(GuestbookVO gbvo) {
		return guestbookDAO.insertGuestbook(gbvo);
	}

	@Override
	public GuestbookVO getGuestbookOneList(String gb2_idx) {
		return guestbookDAO.getGuestbookOneList(gb2_idx);
	}

	@Override
	public int updateGuestbook(GuestbookVO gbvo) {
		return guestbookDAO.updateGuestbook(gbvo);
	}

	@Override
	public int deleteGuestbook(String gb2_idx) {
		return guestbookDAO.deleteGuestbook(gb2_idx);
	}
	
	
}
