<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result4</title>
</head>
<body>
	<h2>결과 : Result4 입니다.</h2>
	<h3>물 : ${water}</h3>
	<h3>결과 : </h3>
		<c:choose>
			<c:when test="${empty carName}">
				<p>출력할 결과가 없습니다</p>
			</c:when>
			<c:otherwise>
				<ul>
					<c:forEach var="k" items="${carName}">
						<li>${k}</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	
	
</body>
</html>