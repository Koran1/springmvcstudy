<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - XML</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
	table{width: 600px; border-collapse: collapse;}
	table, th, td{border: 1px solid blue; text-align: center;}
	
</style>
</head>
<body>
	<button id="btn1">테스트(html)</button>
    <button id="btn2">테스트(xml)</button>
    <button id="btn3">테스트(날씨 xml)</button>
    <button id="btn4">테스트(공공데이터 포털 xml)</button>
    
    <hr>
    <div id="result"></div>
    
    <script type="text/javascript">
    	$("#btn1").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test01",		// 서버 주소
				method: "post",			// 전달 방식
				// data : "",			// 서버에 갈 때 가져갈 데이터(파라미터)
				dataType: "text",		// 가져오는 결과 데이터 타입
				// async : true(비동기 = [기본값]) 또는 false(동기)
				success : function(msg) {
					$("#result").append(msg);
				},
				error : function() {
					alert("읽기 실패!");
				}
			});
		});
    	
    	$("#btn2").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test02",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "xml",		// 가져오는 결과 데이터 타입
				success : function(sb) {
					let table = "<table>";
					table += "<thead><tr><th>회사</th><th>이름</th><th>개수</th></tr></thead>";
					table += "<tbody>";
					$(sb).find("product").each(function(){
						let company = $(this).text();
						let name = $(this).attr("name");
						let count = $(this).attr("count");
						table += "<tr><td>"+company+"</td><td>"+name+"</td><td>"+count+"</td></tr>";
					});
					
					table += "</tbody>";
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
				url : "/test03",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "xml",		// 가져오는 결과 데이터 타입
				success : function(sb) {
					let year = $(sb).find("weather").attr("year");
					let month = $(sb).find("weather").attr("month");
					let day = $(sb).find("weather").attr("day");
					let hour = $(sb).find("weather").attr("hour");
					let date = "<h3>"+year+"."+month+"."+day+" "+hour+"시</h3>";
					
					let table = "<table>";
					table += "<thead><tr><th>지역</th><th>아이콘</th><th>날씨</th><th>기온</th><th>강수량</th></tr></thead>";
					table += "<tbody>";
					$(sb).find("local").each(function(){
						let region = $(this).text();
						let icon = $(this).attr("icon");
						let desc = $(this).attr("desc");
						let ta = $(this).attr("ta");
						let rn_hr1 = $(this).attr("rn_hr1");
						
						table += "<tr><td>"+region+"</td><td><img src='https://www.kma.go.kr/images/icon/NW/NB"+icon+".png'></td><td>"
								+desc+"</td><td>"+ta+"</td><td>"+rn_hr1+"</td></tr>";
					});
					
					table += "</tbody>";
					table += "</table>";
					$("#result").append(date);
					$("#result").append(table);
				},
				error : function() {
					alert("읽기 실패!");
				}
			});
		});
    	
    	$("#btn4").click(function() {
			$("#result").empty();
			$.ajax({
				url : "/test04",		// 서버 주소
				method: "post",			// 전달 방식
				dataType: "text",		// 가져오는 결과 데이터 타입
				success : function(msg) {
					let table = "<div><table>"
					table += "<thead><tr><th>지역</th><th>초미세먼지 [ug/m3]</th><th>미세먼지 [ug/m3]</th><th>오존 [ppm]</th><th>이산화질소 [ppm]</th><th>일산화탄소 [ppm]</th></tr></thead>";
					table += "<tbody>";
					
					$(msg).find("item").each(function(){
						table += "<tr>";
						table += "<td>" + $(this).find("sidoName").text() + "</td>";
						table += "<td>" + $(this).find("pm25Value").text() + "</td>";
						table += "<td>" + $(this).find("pm10Value").text() + "</td>";
						table += "<td>" + $(this).find("o3Value").text() + "</td>";
						table += "<td>" + $(this).find("no2Value").text() + "</td>";
						table += "<td>" + $(this).find("coValue").text() + "</td>";
						table += "</tr>";
					});
						
					table += "</tbody>";
					table += "</table></div>";
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