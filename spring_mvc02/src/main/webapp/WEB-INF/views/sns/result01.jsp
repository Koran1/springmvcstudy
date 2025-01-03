<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SNS - result01</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>카카오 로그인 성공</h2>
	<div id="result"></div>
	
	<!-- 계정과 함께 로그아웃 -->
	<a href="https://kauth.kakao.com/oauth/logout?client_id=1a31dbd4bb00984c5b2d38a62c3d2f0f&logout_redirect_uri=http://localhost:8080/kakaologout">로그아웃</a>	
	
	<script type="text/javascript">
		// $(document.ready(function () {			// 아래랑 똑같음
		$(function() {
			$("#result").empty();
			$.ajax({
				url : "/kakaoUserInfo",
				method : "post",
				dataType : "json",
				success : function(data) {
					const nickname = data.properties.nickname;
					const img = data.properties.profile_image;
					const email = data.kakao_account.email;
					
					console.log(email);
					/* let str = "<h3>${nickname}</h3>";
					str += "<img src='${profileImage}'>"; */
					// $("#result").append(str);
				}, 
				error : function() {
					alert("읽기 실패!");
				}
			})
		})
	</script>
</body>
</html>