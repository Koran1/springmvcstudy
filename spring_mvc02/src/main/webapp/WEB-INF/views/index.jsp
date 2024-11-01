<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h2>Index Page of spring_mvc02 입니다</h2>
	<h3><a href="/sns_login">SNS Login</a></h3>
	<h3><a href="/guestbook2">Guestbook2</a></h3>
	<h3><a href="/bbs">게시판 : bbs</a></h3>
	<h3><a href="/board_list">게시판 : board</a></h3>
	<h3><a href="/findroad">road test</a></h3>
	
	<button onclick="shop_go()"><h3>shopping</h3></button>
	<button onclick="addr_go()"><h3>주소록</h3></button>
	<button type="button" onclick="kakao_map01()">카카오 지도연습 01</button>
	<button type="button" onclick="kakao_map02()">카카오 지도연습 02</button>
	<button type="button" onclick="kakao_map03()">카카오 지도연습 03</button>
	<button type="button" onclick="kakao_map04()">카카오 지도연습 04</button>
	
	<script type="text/javascript">
		function shop_go() {
			location.href="/shop/list";
		}
		function addr_go() {
			location.href="/daum_addr";
		}
		function kakao_map01() {
			location.href = "/kakao_map01";
		}
		function kakao_map02() {
			location.href = "/kakao_map02";
		}
		function kakao_map03() {
			location.href = "/kakao_map03";
		}
		function kakao_map04() {
			location.href = "/kakao_map04";
		}
	</script>
	
</body>
</html>