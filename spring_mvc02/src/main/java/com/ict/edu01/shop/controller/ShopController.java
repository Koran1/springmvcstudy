package com.ict.edu01.shop.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.member.vo.MemberVO;
import com.ict.edu01.shop.service.ShopService;
import com.ict.edu01.shop.vo.CartVO;
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
			if (category == null || category == "") {
				// 초기 설정
				category = "ele002";
			}

			List<ShopVO> shop_list = shopService.getShopList(category);
			if (shop_list != null) {
				mv.addObject("shop_list", shop_list);
				mv.setViewName("shop/product_list");
			} else {
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
			if (shopvo != null) {
				mv.addObject("shopvo", shopvo);
				mv.setViewName("shop/product_content");
			} else {
				mv.setViewName("shop/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}

		return mv;
	}

	@GetMapping("/addCart")
	public ModelAndView addCart(@ModelAttribute("shop_idx") String shop_idx, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			String logchk = (String) session.getAttribute("logchk");
			if (logchk.equals("ok")) {
				// login 정보 가져오기
				MemberVO memvo = (MemberVO) session.getAttribute("mvo");

				// 제품 정보 가져오기
				ShopVO shopvo = shopService.getShopDetail(shop_idx);

				// 제품이 이미 있으면 수량만 + / 없으면 새롭게 +
				CartVO cartvo = shopService.getCartChk(memvo.getM_id(), shopvo.getP_num());

				if (cartvo == null) {
					CartVO cartvo2 = new CartVO();
					cartvo2.setP_num(shopvo.getP_num());
					cartvo2.setP_name(shopvo.getP_name());
					cartvo2.setP_price(shopvo.getP_price());
					cartvo2.setP_saleprice(shopvo.getP_saleprice());
					cartvo2.setP_su(1);
					cartvo2.setM_id(memvo.getM_id());

					int result = shopService.getCartInsert(cartvo2);
					if (result > 0) {
						mv.setViewName("redirect:/shop/detail");
					} else {
						mv.setViewName("shop/error");
					}
				} else {
					int result2 = shopService.getCartUpdate(cartvo);
					if (result2 > 0) {
						mv.setViewName("redirect:/shop/detail");
					} else {
						mv.setViewName("shop/error");
					}
				}
			} else {
				mv.setViewName("sns/login_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("sns/login_error");
		}
		return mv;
	}

	@GetMapping("/showCart")
	public ModelAndView showCart(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		try {
			String logchk = (String) session.getAttribute("logchk");
			if (logchk.equals("ok")) {
				MemberVO memvo = (MemberVO) session.getAttribute("mvo");
				List<CartVO> cart_list = shopService.getCartList(memvo.getM_id());
				mv.addObject("cart_list", cart_list);
				mv.setViewName("shop/cartList");
			} else {
				mv.setViewName("sns/login_error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("sns/login_error");
		}
		return mv;
	}

	@PostMapping("/cart_edit")
	public ModelAndView cartEdit(CartVO cartvo) {
		ModelAndView mv = new ModelAndView();
		try {
			int result = shopService.getCartEdit(cartvo);
			if (result > 0) {
				mv.setViewName("redirect:/shop/showCart");
			} else {
				mv.setViewName("shop/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}
		return mv;
	}

	@GetMapping("cart_delete")
	public ModelAndView cartDelete(String cart_idx) {
		ModelAndView mv = new ModelAndView();
		try {
			int result = shopService.getCartDelete(cart_idx);
			if (result > 0) {
				mv.setViewName("redirect:/shop/showCart");
			} else {
				mv.setViewName("shop/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}
		return mv;
	}

	@GetMapping("/add_form")
	public ModelAndView shopAddForm() {
		return new ModelAndView("shop/product_insert");
	}

	@PostMapping("/product_insert")
	public ModelAndView productInsert(ShopVO shopvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			// 사진 처리
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file_s = shopvo.getP_image_s_file();
			MultipartFile file_l = shopvo.getP_image_l_file();

			UUID uuid = UUID.randomUUID();
			String p_image_s = uuid.toString() + "_" + file_s.getOriginalFilename();
			shopvo.setP_image_s(p_image_s);
			file_s.transferTo(new File(path, p_image_s));

			String p_image_l = uuid.toString() + "_" + file_l.getOriginalFilename();
			shopvo.setP_image_l(p_image_l);
			file_l.transferTo(new File(path, p_image_l));
			
			// DB에 저장하기
			int result = shopService.getProductInsert(shopvo);
			if (result > 0) {
				mv.setViewName("redirect:/shop/list?category=" + shopvo.getCategory());
			} else {
				mv.setViewName("shop/error");
			}

		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("shop/error");
		}

		return mv;
	}

}
