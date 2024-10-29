package com.ict.edu01.sns.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.edu01.sns.vo.KakaoUserResponse;
import com.ict.edu01.sns.vo.KakaoVO;
import com.ict.edu01.sns.vo.NaverUserResponse;
import com.ict.edu01.sns.vo.NaverVO;

@RestController
public class SNSAjaxController {
	
	@RequestMapping(value="/kakaoUserInfo")
	@ResponseBody
	public String getKakaoUserInfo(HttpServletRequest request){
		// session에 저장된 kvo의 access token 사용
		String access_token = ((KakaoVO) request.getSession().getAttribute("kvo")).getAccess_token();
		 
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		String Authorization = "Bearer "+access_token;
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", Authorization);
		headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		return kakaoRequest(apiURL, headers, request);
	}
	
	public String kakaoRequest(String apiURL, Map<String, String> headers, HttpServletRequest request) {
		HttpURLConnection conn = null;
		BufferedReader br = null;
		
		try {
			URL url = new URL(apiURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// header 설정
			for (Entry<String, String> k : headers.entrySet()) {
				conn.setRequestProperty(k.getKey(), k.getValue());
			}
			
			// 응답 코드 확인
			int responseCode = conn.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				System.out.println(result);
				// 사용자 정보를 다른 컨트롤러에서 받기 위해 session에 저장
				Gson gson = new Gson();
				KakaoUserResponse kakaoUser = gson.fromJson(result, KakaoUserResponse.class);
				
				String nickname = kakaoUser.getProperties().getNickname();
				String profileImage = kakaoUser.getProperties().getProfile_image();
				String id = String.valueOf(kakaoUser.getId());				
				String email = kakaoUser.getKakao_acount().getEmail();
				// String fullName = kakaoUser.getProperties().getFullName();
				
				// id 가지고 사용자 DB에 검색해서 id가 있으면 사용자 정보를 더 가져올 수 있다
				
				System.out.println("nickname : "+ nickname);
				System.out.println("profileImage : "+ profileImage);
				System.out.println("id : "+ id);
				System.out.println("email : "+ email);
				
				request.getSession().setAttribute("nickname", nickname);
				request.getSession().setAttribute("profileImage", profileImage);
				
				// request.getSession().setAttribute("fullName", fullName);
				
				return result; 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				conn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return "";
	}
	
	@RequestMapping(value="/naverUserInfo", produces="application/json; charset=utf-8")
	@ResponseBody
	public String getNaverUserInfo(HttpServletRequest request){
		NaverVO nvo = (NaverVO) request.getSession().getAttribute("nvo");
		String access_token = nvo.getAccess_token();
		String token_type = nvo.getToken_type();
		
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String authorization = token_type+" "+access_token;
		
		BufferedReader br = null;
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			conn.setRequestProperty("Authorization", authorization);
			
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				// 1. Session에 정보 저장해주기
				/*
				 * Gson gson = new Gson(); NaverUserResponse naverUser = gson.fromJson(result,
				 * NaverUserResponse.class);
				 * 
				 * String nickname = naverUser.getResponse().getNickname(); String profileImage
				 * = naverUser.getResponse().getProfile_image(); String age =
				 * naverUser.getResponse().getAge(); String gender =
				 * naverUser.getResponse().getGender(); String email =
				 * naverUser.getResponse().getEmail(); String name =
				 * naverUser.getResponse().getName(); String birthdate =
				 * naverUser.getResponse().getBirthyear()+"-" +
				 * naverUser.getResponse().getBirthday();
				 * 
				 * request.getSession().setAttribute("nickname", nickname);
				 * request.getSession().setAttribute("profileImage", profileImage);
				 * request.getSession().setAttribute("age", age);
				 * request.getSession().setAttribute("gender", gender);
				 * request.getSession().setAttribute("email", email);
				 * request.getSession().setAttribute("name", name);
				 * request.getSession().setAttribute("birthdate", birthdate);
				 */
				
				
				return result; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
	
