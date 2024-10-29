<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과-search</title>
<style type="text/css">
	table, th, td{
		text-align: center;
		padding: 10px;
	}
	table, td{
		border: 1px solid red;
	}
	th {
		border: 2px solid blue;	
	}
	table{
		width: 800px;
		border-collapse: collapse;
		margin: 10px auto;
	}
</style>
</head>
<body>
	<h1>결과 : ${dept.dname} [${dept.deptno}] 부서 </h1>
	<h2>인원 : (${list.size()})명</h2>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>사원번호</th>
				<th>이름</th>
				<th>직종</th>
				<th>부서</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr><td colspan="5">"자료가 존재하지 않습니다"</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${k.empno}</td>
							<td>${k.ename}</td>
							<td>${k.job}</td>
							<td>${k.deptno}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
</body>
</html>