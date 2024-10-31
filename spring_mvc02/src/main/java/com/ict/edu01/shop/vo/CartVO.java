package com.ict.edu01.shop.vo;

public class CartVO {
	private String cart_idx, p_num, p_name, m_id;
	private int p_price, p_saleprice, p_su;
	
	public int getTotalPrice() {
		return p_saleprice * p_su;
	}
	
	public String getCart_idx() {
		return cart_idx;
	}

	public void setCart_idx(String cart_idx) {
		this.cart_idx = cart_idx;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public int getP_saleprice() {
		return p_saleprice;
	}

	public void setP_saleprice(int p_saleprice) {
		this.p_saleprice = p_saleprice;
	}

	public int getP_su() {
		return p_su;
	}

	public void setP_su(int p_su) {
		this.p_su = p_su;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

}
