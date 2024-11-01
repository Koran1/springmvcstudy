<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoMap03(마커 표시)</title>
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
	<h2>Kakao Map 03</h2>
	<h2>내 위치 마커 표시, 인포윈도우 표시</h2>
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
			
			// 마커가 표시될 위치입니다 
			let markerPosition  = new kakao.maps.LatLng(lat, lng); 

			// 마커를 생성합니다
			let marker = new kakao.maps.Marker({
			    position: markerPosition
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			
			// 마커가 표시될 위치입니다 
			let markerPosition2  = new kakao.maps.LatLng(33.450701, 126.570667); 

			// 마커를 생성합니다
			let marker2 = new kakao.maps.Marker({
			    position: markerPosition2
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker2.setMap(map);

			
			let iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://ictedu.co.kr" style="color:blue" target="_blank">ICT 인재 개발원</a> </div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			    iwPosition = new kakao.maps.LatLng(lat, lng); //인포윈도우 표시 위치입니다

			// 인포윈도우를 생성합니다
			let infowindow = new kakao.maps.InfoWindow({
			    position : iwPosition, 
			    content : iwContent 
			});
			  
			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
			infowindow.open(map, marker); 
		}
	</script>
</body>
</html>