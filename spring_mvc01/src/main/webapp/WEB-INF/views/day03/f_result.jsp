<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Result Page</title>

</head>
<body>
	<h2>결과 페이지</h2>
	<h3>올린 사람 : ${name}</h3>
	<h3>파일 이름 : ${f_name}</h3>
	<h3>파일 종류 : ${file_type}</h3>
	<h3>파일 크기 : ${size}</h3>
	<h3>수정 날짜 : ${lastday}</h3>
	<!-- 저장 위치와 파일이름이 넘어가야 하지만 위치가 고정되어 있으면 파일이름만 사용 -->
	<a href="/down?f_name=${f_name}">
		<img alt="img" src="<c:url value='resources/upload/${f_name}'/>">
	</a>
	<a href="/down2?f_name=${f_name}">
		<img alt="img" src="<c:url value='resources/upload/${f_name}'/>">
	</a>
</body>
</html>