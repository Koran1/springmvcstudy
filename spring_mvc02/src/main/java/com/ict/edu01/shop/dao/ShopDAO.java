package com.ict.edu01.shop.dao;

import java.util.List;

import com.ict.edu01.shop.vo.CartVO;
import com.ict.edu01.shop.vo.ShopVO;

public interface ShopDAO {
    public List<ShopVO> getShopList(String category) throws Exception;
    public ShopVO getShopDetail(String shop_idx) throws Exception;
    public List<CartVO> getCartList(String m_id) throws Exception;
    public CartVO getCartChk(String m_id, String p_num);
    public int getCartInsert(CartVO cartVO) throws Exception; 
    public int getCartUpdate(CartVO cartVO) throws Exception; 
    public int getCartEdit(CartVO cavo)throws Exception;
    public int getCartDelete(String cart_idx) throws Exception; 
    public int getProductInsert(ShopVO svo) throws Exception;
}
