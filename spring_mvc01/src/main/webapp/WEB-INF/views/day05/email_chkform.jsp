<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email_chkform</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${chk == 1}">
			<h2>이메일 인증 완료</h2>
		</c:when>
		<c:otherwise>
			<h2>이메일 인증 실패</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>