<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SNS - login</title>
</head>
<script type="text/javascript">
	<c:if test="${logchk == 'fail'}">
		alert("로그인 정보가 잘못 되었습니다");
	</c:if>
</script>
<body>
	<h1>SNS 로그인</h1>
	
    <div id="login" style="margin: 30px;">
        <form action="/member_login" method="post">
            <p>    ID : <input type="text" name="m_id" required>    </p>
            <p>    PW : <input type="password" name="m_pw" required></p>
            <input type="submit" value="로그인">
        </form>
    </div>
	
	<!-- 인가(Authorization) 코드 받기 요청 -->
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=1a31dbd4bb00984c5b2d38a62c3d2f0f&redirect_uri=http://localhost:8080/kakaologin&response_type=code">
		<img alt="카카오" src="resources/images/kakao_login_medium_narrow.png">
	</a>
	<hr>
	<a href="	https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=JArbJQzekN3B9KzvZp4b&redirect_uri=http://localhost:8080/naverlogin&state=STATE_STRING">
		<img alt="naver" src="resources/images/btnG_naver.png" style="width: 200px;">
	</a>
	
	<hr>

	<button type="button" onclick="daum_addr()">Daum 주소 api</button>
	
	<script type="text/javascript">
		function daum_addr() {
			location.href = "/daum_addr";
		}
	
	</script>
</body>
</html>