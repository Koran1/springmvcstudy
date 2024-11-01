package com.ict.edu01.sns.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ict.edu01.sns.vo.CoordinateVO;
import com.ict.edu01.sns.vo.DaumAddrVO;
import com.ict.edu01.sns.vo.KakaoVO;
import com.ict.edu01.sns.vo.NaverVO;
import com.ict.edu01.sns.vo.RoadVO;
import com.ict.edu01.sns.vo.RouteInfoVO;
import com.ict.edu01.sns.vo.RouteVO;
import com.ict.edu01.sns.vo.SectionVO;

@Controller
public class SNSLoginController {

	@GetMapping("/sns_login")
	public ModelAndView goSNSLogin() {
		return new ModelAndView("sns/loginform");
	}

	@RequestMapping("/kakaologin")
	public ModelAndView kakaoLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		// 1. 인가 코드 받기
		String code = request.getParameter("code");

		// 2. 토큰 받기 (인가 코드 사용)
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 본문
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=1a31dbd4bb00984c5b2d38a62c3d2f0f");
			sb.append("&redirect_uri=http://localhost:8080/kakaologin");
			sb.append("&code=" + code);
			sb.append("&client_secret=VCIUYRu9VtaTVcOqRJ5DCMEqFvOlGyli");
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드 200 이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 3. 토큰 요청 성공 후 결과 받기 (json 타입)
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();

				// 사용자 정보를 다른 컨트롤러에서 받기 위해 session에 저장
				Gson gson = new Gson();
				KakaoVO kvo = gson.fromJson(result, KakaoVO.class);
				request.getSession().setAttribute("kvo", kvo);
				mv.setViewName("sns/result01");
			} else {
				// 오류 메세지 출력
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				String line = "";
				StringBuffer sbError = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sbError.append(line);
				}
				System.out.println("Error response: " + sbError.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("sns/error");
		}
		return mv;
	}

	@GetMapping("/kakaologout")
	public ModelAndView kakaoLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("sns/loginform");
	}

	@GetMapping("/naverlogin")
	public ModelAndView naverLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		// 1. 인가 코드 받기
		String code = request.getParameter("code");
		String state = request.getParameter("state");

		// 2. 토큰 받기 (인가 코드 사용)
		String reqURL = "https://nid.naver.com/oauth2.0/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 본문
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=JArbJQzekN3B9KzvZp4b");
			sb.append("&client_secret=wdKyUNJCaE");
			sb.append("&code=" + code);
			sb.append("&state=" + state);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드 200 이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 3. 토큰 요청 성공 후 결과 받기 (json 타입)
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				System.out.println(result);

				Gson gson = new Gson();
				NaverVO nvo = gson.fromJson(result, NaverVO.class);
				request.getSession().setAttribute("nvo", nvo);
				mv.setViewName("sns/result02");
			} else {
				// 오류 메세지 출력
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				String line = "";
				StringBuffer sbError = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sbError.append(line);
				}
				System.out.println("Error response: " + sbError.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("sns/error");
		}

		return mv;
	}

	@GetMapping("/findroad")
	public ModelAndView goRoad() {
		return new ModelAndView("road/kakaoroad");
	}

	@GetMapping("/naverlogout")
	public ModelAndView naverLogOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("sns/loginform");
	}

	@GetMapping("/daum_addr")
	public ModelAndView goDaumAddr() {
		return new ModelAndView("sns/addrform");
	}

	@PostMapping("/daum_addr_ok")
	public ModelAndView daumAddrOK(DaumAddrVO davo, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// 로그인 성공한 상태에서 session 안에 있는 사용자 정보 중 m_id 나 사용자 테이블에 pk를 호출한다
		String m_id = (String) session.getAttribute("m_id");
		davo.setM_id(m_id);

		System.out.println(davo.getPostcode());
		System.out.println(davo.getAddress());
		System.out.println(davo.getDetailAddress());
		System.out.println(davo.getExtraAddress());

		mv.setViewName("index");
		return mv;
	}

}
