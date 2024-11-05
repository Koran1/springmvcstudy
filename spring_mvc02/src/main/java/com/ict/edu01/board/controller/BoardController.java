package com.ict.edu01.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu01.board.service.BoardService;
import com.ict.edu01.board.vo.BoardVO;
import com.ict.edu01.common.Paging;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Paging paging;
	
	@RequestMapping("/board_list")
	public ModelAndView goBoardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/list");
		// 1. 전체 개수 구하기
		int count = boardService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 2. 전체 페이지 수 계산하기
		if(paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		}else {
			int totalPage = paging.getTotalRecord() / paging.getNumPerPage();
			if(paging.getTotalRecord() % paging.getNumPerPage() != 0 ) {
				totalPage += 1;
			}
			paging.setTotalPage(totalPage);
		}
		
		// 3. 현재 페이지
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 4. cPage 기준 시작 & 끝
		int limit = paging.getNumPerPage();
		int offset = limit * (paging.getNowPage() - 1);
		paging.setOffset(offset);
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1) / paging.getPagePerBlock()) *paging.getPagePerBlock() +1)
				);
		paging.setEndBlock(
				paging.getBeginBlock() + paging.getPagePerBlock() -1
				);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<BoardVO> list = boardService.getBoardList(offset, limit);
		if(list !=null) {
			mv.addObject("list", list);
			mv.addObject("paging", paging);
		} else {
			return null;
		}
		return mv;
	}
	
	@GetMapping("/board_write")
	public ModelAndView goBoardWrite(@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("board/write");
	}
	
	@PostMapping("/board_write_ok")
	public ModelAndView boardWriteOK(BoardVO boardvo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list");
		try {
			String path =request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = boardvo.getFile_name();
			if(file.isEmpty()) {
				boardvo.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				boardvo.setF_name(f_name);
				file.transferTo(new File(path, f_name));
			}
			
			String pwd = passwordEncoder.encode(boardvo.getPwd());
			boardvo.setPwd(pwd);
			
			int result = boardService.getBoardInsert(boardvo);
			if(result > 0) {
				return mv;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/board_detail")
	public ModelAndView boardDetail(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/view");
		// hit update
		int result = boardService.getBoardHit(idx);
		
		// details
		BoardVO boardvo = boardService.getBoardDetail(idx);
		
		// replies
		
		if(boardvo != null) {
			mv.addObject("boardvo", boardvo);
			return mv;
		}else {
			return null;
		}
		
	}
	
	@GetMapping("/board_down")
	public void boardDown(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(path, "UTF-8");
			
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
	
	@PostMapping("/ans_write")
	public ModelAndView ansWrite(@ModelAttribute("idx") String idx,
			@ModelAttribute("cPage") String cPage) {
		return new ModelAndView("board/ans_write");
	}
	
	@PostMapping("/board_ans_write_ok")
	public ModelAndView ansWriteOK(BoardVO boardVO,
			HttpServletRequest request,
			@ModelAttribute("cPage") String cPage) {
		try {
			// 답글에서만 처리할 일들
			// 1. 원글의 b_groups, b_step, b_lev를 가져오자
			BoardVO boardvo_orig = boardService.getBoardDetail(boardVO.getIdx());
			int b_groups = Integer.parseInt(boardvo_orig.getB_groups());
			int b_step = Integer.parseInt(boardvo_orig.getB_step());
			int b_lev = Integer.parseInt(boardvo_orig.getB_lev());
			
			// 2. step, lev 를 하나씩 증가
			b_step++;
			b_lev++;
			
			// 3. DB 에 업데이트 -> b_groups 기준으로 b_lev를 업데이트하자
			// => b_groups 가 같은 글을 찾아서 기존 데이터의 레벨이 같거나 크면 기존 데이터의 레벨을 증가시키자
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("b_groups", b_groups);
			map.put("b_lev", b_lev);
			
			int result = boardService.getLevUpdate(map);
			boardVO.setB_groups(String.valueOf(b_groups));
			boardVO.setB_step(String.valueOf(b_step));
			boardVO.setB_lev(String.valueOf(b_lev));
			
			ModelAndView mv = new ModelAndView("redirect:/board_list");
			
			// 아래 과정은 write 와 동일
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = boardVO.getFile_name();
			
			if(file.isEmpty()) {
				boardVO.setF_name("");
			}else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				boardVO.setF_name(f_name);
				
				
				file.transferTo(new File(path, f_name));
			}
			
			String pwd_enc = passwordEncoder.encode(boardVO.getPwd());
			boardVO.setPwd(pwd_enc);
			
			int result2 = boardService.getAnsInsert(boardVO);
			if(result2 > 0) {
				return mv;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/board_delete")
	public ModelAndView getBoardDelete(
			@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx
			) {
		return new ModelAndView("board/delete");
	}
	
	@PostMapping("/board_delete_ok")
	public ModelAndView getBoardDeleteOK(
			@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx,
			@RequestParam("pwd") String pwd
			) {
		ModelAndView mv = new ModelAndView();
		BoardVO boardvo = boardService.getBoardDetail(idx);
		String true_pw = boardvo.getPwd();
		if(passwordEncoder.matches(pwd, true_pw)) {
			int result = boardService.getBoardDelete(idx);
			if(result > 0) {
				mv.setViewName("redirect:/board_list");
			}else {
				return null;
			}
		}else {
			mv.addObject("pwdchk", "1");
			mv.setViewName("board/delete");
		}
		
		return mv;
	}
	
	@PostMapping("/board_update")
	public ModelAndView getBoardUpdate(
			@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx
			) {
		ModelAndView mv = new ModelAndView("board/update");
		BoardVO boardvo = boardService.getBoardDetail(idx);
		mv.addObject("boardvo", boardvo);
		return mv;
	}
	
	@PostMapping("/board_update_ok")
	public ModelAndView getBoardUpdateOK(
			BoardVO boardvo,
			@RequestParam("old_f_name") String old_f_name,
			@ModelAttribute("cPage") String cPage,
			@ModelAttribute("idx") String idx,
			HttpServletRequest request 
			) {
		ModelAndView mv = new ModelAndView();
		try {
			String true_pw = boardService.getBoardDetail(idx).getPwd();
			if(passwordEncoder.matches(boardvo.getPwd(), true_pw)) {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = boardvo.getFile_name();
				if(file.isEmpty()) {
					boardvo.setF_name(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString() +"_"+file.getOriginalFilename();
					boardvo.setF_name(f_name);
					
					file.transferTo(new File(path, f_name));
				}
				
				int result = boardService.getBoardUpdate(boardvo);
				if(result > 0) {
					mv.setViewName("redirect:/board_list");
				}else {
					return null;
				}
				
			}else {
				mv.addObject("pwdchk", "1");
				mv.addObject("boardvo", boardvo);
				mv.setViewName("board/update");
			}
		
		return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
