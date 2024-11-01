<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 3px
}

div {
	width: 600px;
	margin: auto;
	text-align: center;
}

#gb_name {
	border: none;
	border-bottom: 1px solid blue;
	background-color: transparent;
}

#gb_name:hover {
	cursor: pointer;
	background-color: lightgray;
}
</style>
</head>
<body>
	<div>
		<h2>방 명 록 2</h2>
		<hr>
		<p><a href="/gb2_write">[ 방명록 쓰기 ]</a></p>

		<table>
			<thead>
				<tr style="background-color: #99ccff">
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="4"><span style="">원하는 정보가 존재하지 않습니다</span></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${list}" var="k" varStatus="cnt">
							<tr>
								<td>${cnt.count}</td>
								<td>${k.gb2_name}</td>
								<td><a href="/gb2_detail?gb2_idx=${k.gb2_idx}">${k.gb2_subject}</a></td>
								<td>${k.gb2_regdate.substring(0,10)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>
</html>