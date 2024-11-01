package com.ict.edu01.bbs.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.bbs.service.BBSService;
import com.ict.edu01.bbs.vo.BBSVO;
import com.ict.edu01.bbs.vo.CommentVO;
import com.ict.edu01.common.Paging;

@Controller
public class BBSController {
	
	@Autowired
	private BBSService bbsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping("/bbs")
	public ModelAndView goBBS(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/list");
		
		// paging 기법 이전 = guestbook
		/*List<BBSVO> list = bbsService.getBBSList();
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}else {
			mv.setViewName("bbs/error");
			return mv;
		}*/
		
		// paging 기법 이후
		// 1. 전체 게시물의 수를 구하자
		int count = bbsService.getTotalCount();
		
		// 2. 전체 페이지 수 계산하기
		paging.setTotalRecord(count);
		
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			int totalPage = paging.getTotalRecord() / paging.getNumPerPage();
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				totalPage += 1;
			}
			paging.setTotalPage(totalPage);
		}
		
		// 3. 파라미터에서 넘어오는 cPage(=보고 싶은 페이지 번호)를 구하자
		String cPage = request.getParameter("cPage");
		
		// 만약에 cPage가 null이면 무조건 1페이지다
		if(cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 4. cPage를 기준으로 begin, end, beginBlock, endBlock 설정
		// 1) Oracle 인 경우 (begin, end 를 구해야 한다)
		// 2) Mariadb, mysql 은 limit, offset 사용
		// offset = limit * (현재페이지 - 1) 	=> 시작지점
		// limit = numPerPage				=> 보여줄 개수
		int limit = paging.getNumPerPage();
		int offset = limit * (paging.getNowPage() - 1);
		paging.setOffset(offset);
		
		// 시작 블럭과 끝 블럭 구하기
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1) / paging.getPagePerBlock()) *paging.getPagePerBlock() +1)
				);
		paging.setEndBlock(
				paging.getBeginBlock() + paging.getPagePerBlock() -1
				);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		mv.addObject("paging", paging);
		
		// DB 연결
		List<BBSVO> pagelist = bbsService.getBBSList(offset, limit);
		mv.addObject("pagelist", pagelist);
		return mv;
	}
	
	@GetMapping("/bbs_write")
	public ModelAndView goBBSWrite() {
		return new ModelAndView("bbs/write");
	}
	
	@PostMapping("/bbs_write_ok")
	public ModelAndView getBBSWriteOK(BBSVO bvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = bvo.getFile_name();
			
			if(file.isEmpty()) {
				bvo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				bvo.setF_name(f_name);
				
				file.transferTo(new File(path, f_name));
			}
			
			String pwd = passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(pwd);
			
			int result = bbsService.getBBSInsert(bvo);
			if(result > 0) {
				mv.setViewName("redirect:/bbs");
			}else {
				mv.setViewName("bbs/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("bbs/error");
		}
		return mv;
	}
	
	@GetMapping("/bbs_detail")
	public ModelAndView goBBSDetail(
			@RequestParam("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage
			) {
		ModelAndView mv = new ModelAndView("bbs/detail");
		// 조회수 증가
		int result = bbsService.getHitUpdate(b_idx);
		
		// 상세보기
		BBSVO bvo = bbsService.getBBSDetail(b_idx);
		
		// 댓글 가져오기
		List<CommentVO> clist = bbsService.getCommentList(b_idx);
		
		mv.addObject("clist", clist);
		mv.addObject("bvo",bvo);
		return mv;
	}
	
	@GetMapping("/bbs_down")
	public void goBBSDownload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/")+f_name;
			String r_path = URLEncoder.encode(f_name, "UTF-8");
			
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
	
	@GetMapping("/board")
	public ModelAndView goBoard() {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	@PostMapping("/comment_insert")
	public ModelAndView commentInsert(
			CommentVO cvo, 
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView();
		int result = bbsService.getCommentInsert(cvo);
		if(result > 0) {
			mv.setViewName("redirect:/bbs_detail");
		}
		else {
			mv.setViewName("bbs/error");
		}
		return mv;
	}
	
	@PostMapping("/comment_delete")
	public ModelAndView commentDelete(
			@RequestParam("c_idx") String c_idx,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView();
		int result = bbsService.getCommentDelete(c_idx);
		if(result > 0) {
			mv.setViewName("redirect:/bbs_detail");
		}
		else {
			mv.setViewName("bbs/error");
		}
		return mv;
	}
	
	@PostMapping("/bbs_update")
	public ModelAndView goBBSUpdate(
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView();
		BBSVO bvo = bbsService.getBBSDetail(b_idx);
		if(bvo != null) {
			mv.setViewName("bbs/update");
			mv.addObject("bvo", bvo);
		} else {
			mv.setViewName("bbs/error");
		}
		return mv;
	}
	
	@PostMapping("/bbs_update_ok")
	public ModelAndView getBBSUpdateOK(
			BBSVO bvo,
			HttpServletRequest request,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView();	
		
		BBSVO bvo2 = bbsService.getBBSDetail(b_idx);
		
		if(passwordEncoder.matches(bvo.getPwd(), bvo2.getPwd())) {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = bvo.getFile_name();
				String old_f_name = bvo.getOld_f_name();
				
				if(file.isEmpty()) {
					bvo.setF_name(old_f_name);
				} else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString() + "_" + file.getOriginalFilename();
					bvo.setF_name(f_name);
					
					// 실제 업로드
					file.transferTo(new File(path, f_name));
				}
				
				int result = bbsService.getBBSUpdate(bvo);
				if(result > 0) {
					mv.setViewName("redirect:/bbs_detail");
				} else {
					mv.setViewName("bbs/error");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			mv.setViewName("bbs/update");
			mv.addObject("bvo", bvo);
			mv.addObject("pwdchk", "fail");
		}
		
		return mv;
	}
	
	@PostMapping("/bbs_delete")
	public ModelAndView goBBSDelete(
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("bbs/delete");
	}
	
	@PostMapping("/bbs_delete_ok")
	public ModelAndView getBBSDeleteOK(
			@RequestParam("pwd") String pwd,
			@ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView();
		
		// 비밀번호 체크
		BBSVO bvo = bbsService.getBBSDetail(b_idx);
		String real_pwd = bvo.getPwd();
		
		if(passwordEncoder.matches(pwd, real_pwd)) {
			int result = bbsService.getBBSDelete(b_idx);
			if(result > 0 ) {
				mv.setViewName("redirect:/bbs");
			}else {
				mv.setViewName("bbs/error");
			}
		}else {
			mv.addObject("pwdchk", "fail");
			mv.setViewName("bbs/delete");
		}
		return mv;
	}
	
}
