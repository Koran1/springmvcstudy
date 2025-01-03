<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoMap01</title>
</head>
<body>
	<h2>Kakao Map 01</h2>
	<div id="map" style="width: 100%; height: 350px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ab528374e287b067bf7ce3808786127"></script>
	<script>
		let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨 (1 ~ 14 까지 가능)
		};

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		let map = new kakao.maps.Map(mapContainer, mapOption);
	</script>
</body>
</html>