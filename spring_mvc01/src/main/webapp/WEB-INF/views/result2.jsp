<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result2</title>
</head>
<body>
	<h2>결과2 : result2.jsp 입니다.</h2>
	<h3>dogName</h3>
	<c:choose>
		<c:when test="${empty dogName}">
		<p>출력할 내용이 없습니다</p>
		</c:when>
		<c:otherwise>
			<ul>
				<c:forEach var="k" items="${dogName}">
					<li>${k}</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	<hr>
	<h3>catName</h3>
	<c:choose>
		<c:when test="${empty catName}">
		<p>출력할 내용이 없습니다</p>
		</c:when>
		<c:otherwise>
			<ul>
				<c:forEach var="k" items="${catName}">
					<li>${k}</li>
				</c:forEach>
			</ul>	
		</c:otherwise>
	</c:choose>
	<hr>
	<h3>carol</h3>
	<c:choose>
		<c:when test="${empty carol}">
		<p>출력할 내용이 없습니다</p>
		</c:when>
		<c:otherwise>
			<ul>
				<c:forEach var="k" items="${carol}">
					<li>${k}</li>
				</c:forEach>
			</ul>	
		</c:otherwise>
	</c:choose>
</body>
</html>