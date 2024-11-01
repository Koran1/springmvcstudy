<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoRoad</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>Map</h1>
	<button onclick="getRoadLine()">경로 그리기</button>
	<button onclick="addAnim()">경로 그리기</button>

	<div id="map" style="width: 100%; height: 350px;"></div>

	<div class="container">
		<input type="checkbox">안녕하시오
	</div>

	<script>
	document.querySelectorAll('input[type="checkbox"]').forEach(cb => {
        cb.addEventListener('change', () => {
            // Check if the checkbox is unchecked
            if (!cb.checked) {
                alert('Checkbox is unchecked!');
            }
        });
    });
	
	let arr = ['a', 'b', 'b', 'c'];

	arr = arr.filter((element) => element !== 'b');

	document.writeln(arr); // ['a', 'b', 'b', 'c']
	
	function addAnim() {
		const travelPlan = document.createElement('button');
        travelPlan.classList.add('draggable');
        travelPlan.setAttribute('draggable', "true");
        travelPlan.innerHTML ='🦊';
        document.querySelector('.container').insertBefore(travelPlan, null);
    	
        test1();
	}
	
	function test1(){
		

const draggables = document.querySelectorAll(".draggable");
const containers = document.querySelectorAll(".container");

draggables.forEach(draggable => {
  draggable.addEventListener("dragstart", () => {
    draggable.classList.add("dragging");
  });

  draggable.addEventListener("dragend", () => {
    draggable.classList.remove("dragging");
  });
});

containers.forEach(container => {
  container.addEventListener("dragover", e => {
    e.preventDefault();
    const afterElement = getDragAfterElement(container, e.clientX);
    const draggable = document.querySelector(".dragging");
    if (afterElement === undefined) {
      container.appendChild(draggable);
    } else {
      container.insertBefore(draggable, afterElement);
    }
  });
});

function getDragAfterElement(container, x) {
  const draggableElements = [
    ...container.querySelectorAll(".draggable:not(.dragging)"),
  ];

  return draggableElements.reduce(
    (closest, child) => {
      const box = child.getBoundingClientRect();
      const offset = x - box.left - box.width / 2;
      // console.log(offset);
      if (offset < 0 && offset > closest.offset) {
        return { offset: offset, element: child };
      } else {
        return closest;
      }
    },
    { offset: Number.NEGATIVE_INFINITY },
  ).element;
}
	}
	test1();
</script>


	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ab528374e287b067bf7ce3808786127"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		};

		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);

		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    // lat = y / lng = x
		    addMarker(latlng.getLat(), latlng.getLng());
		    positions.push(latlng.getLng());
		    positions.push(latlng.getLat());
		});
		
		var positions = [];
		
		function addMarker(lat, lng) {
            let marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(lat, lng),
                map: map
            });
        }
		
		const linePath = [];
		
		function getRoadLine() {
			$.ajax({
				url : "/kakaoRoadLine",
				method : "get",
				data : {positions : positions},
				dataType : "json",
				success : function(data) {
					console.log(data);
					data.forEach(route => {
						route.routes[0].sections[0].roads.forEach(item => {
							  item.vertexes.forEach((vertex, index) => {
								if (index % 2 === 0) {
									linePath.push(new kakao.maps.LatLng(item.vertexes[index + 1], item.vertexes[index]));
								}
							});
						});
					})

					  
					var polyline = new kakao.maps.Polyline({
					  path: linePath,
					  strokeWeight: 5,
					  strokeColor: '#0000ff',
					  strokeOpacity: 0.5,
					  strokeStyle: 'solid'
					}); 
					polyline.setMap(map);
				}, 
				error : function() {
					alert("읽기 실패!");
				}
			})
			
		}
		
	</script>

</body>
</html>