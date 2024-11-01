package com.ict.edu01.shop.vo;

import org.springframework.web.multipart.MultipartFile;

public class ShopVO {
	private MultipartFile p_image_s_file, p_image_l_file;
	private String shop_idx, category, p_num, p_name, p_company, p_image_s, p_image_l, p_content, p_date;
	private int p_price, p_saleprice;

	// 할인률 계산
	public int getPercent() {
		return (int) ((p_price - p_saleprice) * 100.0 / p_price);
	}

	public MultipartFile getP_image_s_file() {
		return p_image_s_file;
	}

	public void setP_image_s_file(MultipartFile p_image_s_file) {
		this.p_image_s_file = p_image_s_file;
	}

	public MultipartFile getP_image_l_file() {
		return p_image_l_file;
	}

	public void setP_image_l_file(MultipartFile p_image_l_file) {
		this.p_image_l_file = p_image_l_file;
	}

	public String getShop_idx() {
		return shop_idx;
	}

	public void setShop_idx(String shop_idx) {
		this.shop_idx = shop_idx;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getP_company() {
		return p_company;
	}

	public void setP_company(String p_company) {
		this.p_company = p_company;
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

	public String getP_image_s() {
		return p_image_s;
	}

	public void setP_image_s(String p_image_s) {
		this.p_image_s = p_image_s;
	}

	public String getP_image_l() {
		return p_image_l;
	}

	public void setP_image_l(String p_image_l) {
		this.p_image_l = p_image_l;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

}
