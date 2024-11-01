package com.ict.edu01.board.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/board_list")
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
	public ModelAndView goBoardWrite() {
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
	public ModelAndView boardDetail(@ModelAttribute("cPage") String cPage, @RequestParam("idx") String idx) {
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
	
}
