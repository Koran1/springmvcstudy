package com.ict.edu01.guestbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.guestbook.service.GuestbookService;
import com.ict.edu01.guestbook.vo.GuestbookVO;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/guestbook2")
	public ModelAndView goGuestbookList() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<GuestbookVO> list = guestbookService.getGuestbookList();
		mv.addObject("list", list);
		return mv;
	}
	
	@GetMapping("/gb2_write")
	public ModelAndView goGuestbookWrite() {
		return new ModelAndView("guestbook2/write");
	}
	
	@PostMapping("/gb2_write_ok")
	public ModelAndView goGuestbookWriteOK(
			HttpServletRequest request ,
			GuestbookVO gbvo) {
		ModelAndView mv = new ModelAndView();
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = gbvo.getGb2_file_name();
			
			if(file.isEmpty()) {
				gbvo.setGb2_f_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				gbvo.setGb2_f_name(f_name);
				
				file.transferTo(new File(path, f_name));
			}
			
			// 비밀번호 암호화
			String pwd = passwordEncoder.encode(gbvo.getGb2_pw());
			gbvo.setGb2_pw(pwd);
			
			int result = guestbookService.insertGuestbook(gbvo);
			if(result > 0) {
				mv.setViewName("redirect:/guestbook2");
			}else {
				mv.setViewName("guestbook2/error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("guestbook2/error");
		}
		return mv;
	}
	
	@GetMapping("/gb2_detail")
	public ModelAndView goGuestbookDetail(
			@RequestParam("gb2_idx") String gb2_idx) {
		ModelAndView mv = new ModelAndView("");
		GuestbookVO gbvo = guestbookService.getGuestbookOneList(gb2_idx);
		if(gbvo != null) {
			mv.addObject("onelist", gbvo);
			mv.setViewName("guestbook2/onelist");
		}
		else {
			mv.setViewName("guestbook2/error");
		}
		return mv;
	}
	
	@GetMapping("/gb2_down")
	public void guestbookDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/")+f_name;
			String r_path = URLEncoder.encode(f_name, "UTF-8");
			
			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="+r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			
			OutputStream out = response.getOutputStream();
			
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@PostMapping("/gb2_delete")
	public ModelAndView goGuestbookDelete(@ModelAttribute("gb2_idx") String gb2_idx) {
		return new ModelAndView("guestbook2/delete");
	}
	
	@PostMapping("/gb2_delete_ok")
	public ModelAndView goGuestbookDeleteOK(GuestbookVO gbvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String gb2_idx = gbvo.getGb2_idx();
		// 비밀번호 검사
		GuestbookVO gbvo2 = guestbookService.getGuestbookOneList(gb2_idx);
		
		if(passwordEncoder.matches(gbvo.getGb2_pw(), gbvo2.getGb2_pw())) {
			int result = guestbookService.deleteGuestbook(gb2_idx);
			if(result > 0) {
				mv.setViewName("redirect:/guestbook2");
			}
		}else {
			mv.addObject("pwdchk", "fail");
			mv.addObject("gb2_idx", gb2_idx);
			mv.setViewName("guestbook2/delete");
		}
		return mv;
	}
	
	@PostMapping("/gb2_update")
	public ModelAndView goGuestbookUpdate(@ModelAttribute("gb2_idx") String gb2_idx) {
		ModelAndView mv = new ModelAndView("guestbook2/update");
		GuestbookVO gbvo = guestbookService.getGuestbookOneList(gb2_idx);
		if(gbvo != null) {
			mv.addObject("onelist", gbvo);
		}else {
			return new ModelAndView("guestbook2/error");
		}
		return mv;
	}
	
	@PostMapping("/gb2_update_ok")
	public ModelAndView guestbookUpdateOK(GuestbookVO gbvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String gb2_idx = gbvo.getGb2_idx();
		
		// 비밀번호 검사
		GuestbookVO gbvo2 = guestbookService.getGuestbookOneList(gb2_idx);
		
		if(passwordEncoder.matches(gbvo.getGb2_pw(), gbvo2.getGb2_pw())) {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = gbvo.getGb2_file_name();
				String old_f_name = gbvo.getGb2_old_f_name();
				
				if(file.isEmpty()) {
					gbvo.setGb2_f_name(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString()+"_"+file.getOriginalFilename();
					gbvo.setGb2_f_name(f_name);
					
					file.transferTo(new File(path, f_name));
				}
				
				String newpw = passwordEncoder.encode(gbvo.getGb2_pw());
				gbvo.setGb2_pw(newpw);
				
				int result = guestbookService.updateGuestbook(gbvo);
				if(result > 0) {
					mv.setViewName("redirect:/gb2_detail?gb2_idx="+gb2_idx);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mv.addObject("pwdchk", "fail");
			mv.addObject("onelist", gbvo2);
			mv.setViewName("guestbook2/update");
		}
		return mv;
	}
	
}
