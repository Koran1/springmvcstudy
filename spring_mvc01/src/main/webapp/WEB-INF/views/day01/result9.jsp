<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result9</title>
</head>
<body>
	<h2> 결과 : result9.jsp</h2>
	<h3> 계산 : ${su1} ${op} ${su2} = ${result}</h3>
	<h3> 취미 : </h3>
	<ul>
		<c:choose>
			<c:when test="${empty hobby}">
				<li> 취미가 없습니다! </li>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${hobby}">
					<li>${k}</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
	<h3> cPage : ${cPage}</h3>
</body>
</html>