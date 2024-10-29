package com.ict.edu03.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu03.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class FileController {
	
	// GET 방식으로 오면 바로 에러 처리
	@GetMapping(value = {"/f_up1", "/f_up2", "/f_up3", "/f_up4"})
	public ModelAndView fileUpGet() {
		return new ModelAndView("day03/error");
	}
	
	// cos 라이브러리 사용
	@PostMapping("/f_up1")
	public ModelAndView fileUp(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mv = new ModelAndView("day03/f_result");
			// 실제로는 프로젝트가 아닌 다른 곳에 저장할 수도 있다 (AWS 의 S3, 서버의 특정 위치 등등)
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");

			// cos 라이브러리를 사용하면 request 대신에 MultipartRequest를 사용해야 한다
			MultipartRequest mr =
					new MultipartRequest(request, 				// request 대신하기 위해서 request 를 받음
							path,								// 저장 위치
							500*1024*1024,						// 업로드 최대 용량 : 500 MB
							"utf-8",							// 한글처리
							new DefaultFileRenamePolicy());		// 같은 이름의 파일이 있을 때 이름 뒤에 숫자를 붙여서 rename 시켜줌
			
			String name = mr.getParameter("name");				// 업로드한 사람의 이름
			String f_name = mr.getFilesystemName("f_name");		// 저장할 때의 이름
			String file_type = mr.getContentType("f_name");		// 파일 종류
			
			File file = mr.getFile("f_name");
			long size = file.length() / 1024;					// 파일 크기를 KB로 나타낸다
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(file.lastModified());	// 최종 수정 날짜
			
			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file_type", file_type);
			mv.addObject("size", size);
			mv.addObject("lastday", lastday);
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day03/error");
		}
	}
	
	@GetMapping("/down")
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(f_name, "utf-8");
			
			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="+r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			int b;
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			
			while((b=bis.read()) != -1) {
				bos.write(b);
				bos.flush();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@PostMapping("/f_up2")
	public ModelAndView fileUp2(
			@RequestParam("name") String name,
			@RequestParam("f_name") MultipartFile fname,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("/day03/f_result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			// 파일 업로드할 때의 이름만 존재한다
			// 즉, 같은 이름의 파일을 업로드하면 업로드 되지 않는다 = 단점
			// 1. 보통 파일이름 뒤에 id 와 업로드 날짜를 붙인다
			// 2. UUID(Universally Unique Identifier = 범용 고유 식별자) 를 생성해서 붙인다.
			//	  => 중복 되지 않는 유일한 값을 구성하고자 할 때 주로 사용
			String f_name = UUID.randomUUID().toString()+"_"+fname.getOriginalFilename();
			String file_type = fname.getContentType();
			long size = fname.getSize()/1024;
			
			// 업로드 - byte[] in 부분 = 파일을 byte[] 로 만들어서 지정한 File에 복사한다
			File out = new File(path, f_name);
			FileCopyUtils.copy(fname.getBytes(), out);
			
			long lastModified = out.lastModified();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(lastModified);	
			
			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file_type", file_type);
			mv.addObject("size", size);
			mv.addObject("lastday", lastday);
			
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day03/error");
		}
	}
	
	@GetMapping("/down2")
	public void fileDown2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			String r_path = URLEncoder.encode(f_name, "utf-8");
			
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
	
	// 정보를 VO 로 받자
	@PostMapping("/f_up4")
	public ModelAndView fileUp4(
			FileVO fvo,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("/day03/f_result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			MultipartFile f_name = fvo.getF_name();
			String fname = UUID.randomUUID().toString()+"_"+f_name.getOriginalFilename();
			String file_type = f_name.getContentType();
			long size = f_name.getSize()/1024;
			
			byte[] in = f_name.getBytes();
			File out = new File(path, fname);
			FileCopyUtils.copy(in, out);
			
			long lastModified = out.lastModified();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(lastModified);	
			
			mv.addObject("name", fvo.getName());
			mv.addObject("f_name", fname);
			mv.addObject("file_type", file_type);
			mv.addObject("size", size);
			mv.addObject("lastday", lastday);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("day03/error");
		}
	}
}
