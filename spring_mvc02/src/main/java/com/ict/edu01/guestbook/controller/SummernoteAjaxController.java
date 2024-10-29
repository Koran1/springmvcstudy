package com.ict.edu01.guestbook.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ict.edu01.guestbook.vo.ImageVO;

@RestController
public class SummernoteAjaxController {
	
	@RequestMapping(value="/saveImg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveImg(ImageVO ivo, HttpServletRequest request) {
		
		// Map 으로 반환 받으면 자동으로 JSON 형식으로 만들어준다
		try {
			Map<String, String> map = new HashMap<String, String>();
			MultipartFile file = ivo.getS_file();
			String fname = null;
			if(file.getSize() > 0) {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				UUID uuid = UUID.randomUUID();
				fname = uuid.toString() + "_" + file.getOriginalFilename();
				
				// 실제 업로드
				file.transferTo(new File(path, fname));
			}
			
			map.put("path", "resources/upload");
			map.put("fname", fname);
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
