package com.ict.edu01.guestbook.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ict.edu01.guestbook.vo.GuestbookVO;

@Repository
public class GuestbookDAOImpl implements GuestbookDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<GuestbookVO> getGuestbookList() {
		return sqlSessionTemplate.selectList("guestbook2.list");
	}

	@Override
	public int insertGuestbook(GuestbookVO gbvo) {
		return sqlSessionTemplate.insert("guestbook2.insert", gbvo);
	}

	@Override
	public GuestbookVO getGuestbookOneList(String gb2_idx) {
		return sqlSessionTemplate.selectOne("guestbook2.onelist", gb2_idx);
	}

	@Override
	public int updateGuestbook(GuestbookVO gbvo) {
		return sqlSessionTemplate.update("guestbook2.update", gbvo);
	}

	@Override
	public int deleteGuestbook(String gb2_idx) {
		return sqlSessionTemplate.delete("guestbook2.delete", gb2_idx);
	}
	
	
}
