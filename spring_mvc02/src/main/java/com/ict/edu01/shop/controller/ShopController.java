package com.ict.edu01.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.shop.service.ShopService;
import com.ict.edu01.shop.vo.ShopVO;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/list")
	public ModelAndView getShopList(String category) {
		ModelAndView mv = new ModelAndView();
		try {
			if(category == null || category == "") {
				// 초기 설정
				category = "ele002";
			}
			
			List<ShopVO> shop_list = shopService.getShopList(category);
			if(shop_list != null) {
				mv.addObject("shop_list", shop_list	);
				mv.setViewName("shop/product_list");
			}
			else {
				mv.setViewName("shop/error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}
		
		return mv;
	}
	
	@GetMapping("/detail")
	public ModelAndView goShopDetail(@RequestParam("shop_idx") String shop_idx) {
		ModelAndView mv = new ModelAndView();
		try {
			ShopVO shopvo = shopService.getShopDetail(shop_idx);
			if(shopvo != null) {
				mv.addObject("shopvo", shopvo);
				mv.setViewName("shop/product_content");
			}else {
				mv.setViewName("shop/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}
		
		return mv;
	}
	
	
}
