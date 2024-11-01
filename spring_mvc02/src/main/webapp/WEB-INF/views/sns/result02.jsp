<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>네이버 로그인 성공</h2>
	<div id="result"></div>
	
	<!-- 계정과 함께 로그아웃 -->
	<a href="/naverlogout">로그아웃</a>	
	
	<script type="text/javascript">
		// $(document.ready(function () {			// 아래랑 똑같음
		$(function() {
			$("#result").empty();
			$.ajax({
				url : "/naverUserInfo",
				method : "post",
				dataType : "json",
				success : function(data) {
					// ajax에서 처리하기
					let nickname = data.response.nickname;
					let profile_image = data.response.profile_image;
					let age = data.response.age;
					let gender = data.response.gender;
					let email = data.response.email;
					let name = data.response.name;
					let birthdate = data.response.birthyear + "-" + data.response.birthday;
					
					let str = "";
					str += "<h3>nickname : "+nickname+"</h3>";
					str += "<img src='"+profile_image+"' style='width:200px'>";
					str += "<h3>age : "+age+"</h3>";
					str += "<h3>gender : "+gender+"</h3>";
					str += "<h3>email : "+email+"</h3>";
					str += "<h3>name : "+name+"</h3>";
					str += "<h3>birthdate : "+birthdate+"</h3>";
					$("#result").append(str);
				}, 
				error : function() {
					alert("읽기 실패!");
				}
			})
		});
	</script>
</body>
</html>