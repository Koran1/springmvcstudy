<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
table {
	margin: 10px auto;
	width: 800px;
	border-collapse: collapse;
	font-size: 12px;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<jsp:include page="top.jsp" />
	<table>
		<tr>
			<td colspan="6">:: 장바구니 내용</td>
		</tr>
		<tr bgcolor="#dedede">
			<th>제품번호</th>
			<th width="25%">제품명</th>
			<th>단가</th>
			<th>수량</th>
			<th>금액</th>
			<th>삭제</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty cart_list}">
				<tr align="center">
					<td colspan="6"><b>장바구니가 비었습니다.</b></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="k" items="${cart_list}">
					<tr align="center">
						<td>--</td>
						<td>${k.p_name}</td>
						<td>정가:${k.p_price}<br>
						<font color="red"> 세일가격: ${k.p_saleprice}</font>
						</td>
						<td>
							${k.p_su}
							<!-- 수량 조정 폼 --> <!------------------>
						</td>
						<td>${k.getTotalPrice()}</td>
						<td><input type="button" value="삭제"
							style="border: 1 solid black; cursor: pointer"
							onclick="">
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="5" align="right">총 결재액 :</td>
			<td></td>
		</tr>
	</table>

</body>
</html>