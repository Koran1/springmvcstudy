<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - JSON</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
	table{width: 100%; border-collapse: collapse;}
	table, th, td{border: 1px solid black; text-align: center;}
	td{padding: 5px;}
	img{width: 100px; height: 100px;}
	#taMin{color: blue;}
	#taMax{color: red;}
</style>
</head>
<body>
	<button id="btn1">json 테스트 (수제)</button>
    <button id="btn2">json 테스트 (makeup)</button>
    <button id="btn3">json 테스트 (공공데이터)</button>
    <hr>
    
    <div id="result"></div>
    
	<script type="text/javascript">
		$("#btn1").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test05",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "json",		// 가져오는 결과 데이터 타입
				success : function(data) {
					let table = "<table>";
					table += "<thead><tr><th>Name</th><th>Scope</th></tr></thead>";
					table += "<tbody>"
					$.each(data, function(index, obj){
						let name = obj.name;
						let scope = obj.scope;
						
						table += "<tr>"
						table += "<td>" + name + "</td>";
						table += "<td>" + scope + "</td>";
	   					table += "</tr>"
					});
					
					table += "</tbody>"
					table += "</table>";
					$("#result").append(table);
				},
				error : function() {
					alert("읽기 실패!");
				}
			});
		});
		
		$("#btn2").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test06",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "json",		// 가져오는 결과 데이터 타입
				success : function(data) {
					let table = "<table>";
					table += "<thead><tr><th>ID</th><th>Brand</th><th>Name</th><th>Price</th>"+
					"<th>Image</th><th>Description</th></tr></thead>";
					table += "<tbody>"
					$.each(data, function(index, obj){
						// 최대 개수 제한
						if(index > 10) return false;
						
						let id = obj.id;
						let brand = obj.brand;
						let name = obj.name;
						let price = obj.price;
						let img = obj.image_link;
						let desc = obj.description;
						let prodLink = obj.product_link;
						
						table += "<tr>"
						table += "<td>" + id + "</td>";
						table += "<td>" + brand + "</td>";
						table += "<td><a href=\"" + prodLink + "\">" + name + "</a></td>";
						table += "<td>$" + price + "</td>";
						table += "<td><img alt=\"img\" src=\"" + img + "\"></td>";
						table += "<td>" + desc + "</td>";
	   					table += "</tr>"
					});
					
					table += "</tbody>"
					table += "</table>";
					$("#result").append(table);
				},
				error : function() {
					alert("읽기 실패!");
				}
			});
		});
		
		$("#btn3").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test07",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "json",		// 가져오는 결과 데이터 타입
				success : function(data) {
					let item = data.response.body.items.item;
					let table = "<table>"
					table += "<thead><tr><th>날짜</th><th>예상최저기온</th><th>예상최고기온</th></tr></thead>";
					
					table += "<tbody>";
					
					$.each(item, function(index, obj) {
						for (let i = 3; i < 11; i++) {
							let f_Date = new Date();
							f_Date.setDate(f_Date.getDate() + (i));
							
							// 날짜 형식을 YYYY-MM-DD로 변환
							let r_date = f_Date.toISOString().split('T')[0];
							
							table += "<tr>";
							table += "<td>" + r_date + "</td>";
							table += "<td id=\"taMin\">" + obj["taMin"+i]+ "</td>";
							table += "<td id=\"taMax\">" + obj["taMax"+i]+ "</td>";
							table += "</tr>";
						}
					});
					table += "</tbody>";
					
					table += "</table>"
					$("#result").append("<h2>서울 날씨</h2>");
					$("#result").append(table);
				},
				error : function() {
					alert("읽기 실패!");
				}
			});
		});
	</script>
	
	
	
</body>
</html>