<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoMap02</title>
<script type="text/javascript">
	let lat = "";
	let lng = "";
	navigator.geolocation.getCurrentPosition(function(position){
		lat = position.coords.latitude;
		lng = position.coords.longitude;
		geo_maps(lat, lng);
	});
</script>
</head>
<body>
	<h2>Kakao Map 02</h2>
	<a href="/index"></a>
	
	<div id="map" style="width: 100%; height: 350px;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ab528374e287b067bf7ce3808786127"></script>
	<script>
		function geo_maps(lat, lng){
			let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
				level : 3
				// 지도의 확대 레벨 (1 ~ 14 까지 가능)
			};
	
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			let map = new kakao.maps.Map(mapContainer, mapOption);
		}
	</script>
</body>
</html>