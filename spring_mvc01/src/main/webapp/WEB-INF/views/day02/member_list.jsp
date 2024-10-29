<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList 보기</title>
<style type="text/css">
	table, th, td{
		border: 1px solid black;
	}
	table, th, td{
		border-collapse: collapse;
	}
	th, td{
		padding: 10px;
		text-align: center;
	}
</style>
</head>
<body>
	<h2>MemberList 결과</h2>
	<c:choose>
		<c:when test="${empty list}">
			<h3>보여줄 결과가 없습니다</h3>
		</c:when>
		<c:otherwise>
			<table>
				<tr><th>m_idx</th><th>m_id</th><th>m_pw</th><th>m_name</th><th>m_age</th><th>m_reg</th></tr>
				<c:forEach var="k" items="${list}">
					<tr><td>${k.m_idx}</td><td>${k.m_id}</td><td>${k.m_pw}</td>
					<td>${k.m_name}</td><td>${k.m_age}</td><td>${k.m_reg}</td></tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>