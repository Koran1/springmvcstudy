package com.ict.edu01.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return sqlSessionTemplate.selectList("shop.cartlist", m_id);
	}

	@Override
	public CartVO getCartChk(String m_id, String p_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		map.put("p_num", p_num);
		return sqlSessionTemplate.selectOne("shop.cartchk", map);
	}

	@Override
	public int getCartInsert(CartVO cartVO) throws Exception {
		return sqlSessionTemplate.insert("shop.cartinsert", cartVO);
	}

	@Override
	public int getCartUpdate(CartVO cartVO) throws Exception {
		return sqlSessionTemplate.update("shop.cartupdate", cartVO);
	}

	@Override
	public int getCartEdit(CartVO cavo) throws Exception {
		return sqlSessionTemplate.update("shop.cartedit", cavo);
	}

	@Override
	public int getCartDelete(String cart_idx) throws Exception {
		return sqlSessionTemplate.delete("shop.cartdelete", cart_idx);
	}

	@Override
	public int getProductInsert(ShopVO svo) throws Exception {
		return sqlSessionTemplate.insert("shop.productinsert", svo);
	}

}
