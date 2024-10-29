package com.ict.edu01.shop.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu01.shop.vo.CartVO;
import com.ict.edu01.shop.vo.ShopVO;

@Repository
public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<ShopVO> getShopList(String category) throws Exception {
		return sqlSessionTemplate.selectList("shop.shop_list", category);
	}

	@Override
	public ShopVO getShopDetail(String shop_idx) throws Exception {
		return sqlSessionTemplate.selectOne("shop.detail", shop_idx);
	}

	@Override
	public List<CartVO> getCartList(String m_id) throws Exception {
		return null;
	}

	@Override
	public CartVO getCartChk(String m_id, String p_num) {
		return null;
	}

	@Override
	public int getCartInsert(CartVO cartVO) throws Exception {
		return 0;
	}

	@Override
	public int getCartUpdate(CartVO cartVO) throws Exception {
		return 0;
	}

	@Override
	public int getCartEdit(CartVO cavo) throws Exception {
		return 0;
	}

	@Override
	public int getCartDelete(String cart_idx) throws Exception {
		return 0;
	}

	@Override
	public int getProductInsert(ShopVO svo) throws Exception {
		return 0;
	}

}
