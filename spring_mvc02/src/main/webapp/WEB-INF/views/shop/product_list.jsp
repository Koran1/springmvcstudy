<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product list</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 800px;
	border-collapse: collapse;
	font-size: 12pt;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
}
img{
	width:100px;
}
</style>
</head>
<body>

	<jsp:include page="top.jsp" />
	
	<table>
		<thead>
			<tr bgcolor="#dedede">
				<th width="10%">제품번호</th>
				<th width="10%">이미지</th>
				<th width="35%">제품명</th>
				<th width="20%">제품가격</th>
				<th width="25%">비고</th>
			</tr>
		</thead>
		<tbody>
				<c:choose>
					<c:when test="${empty shop_list}">
					<tr align="center">
						<td colspan="5">제품 준비중</td>
					</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${shop_list}">
							<tr align="center">
								<td>${k.shop_idx}</td>
								<td><img src="/resources/images/${k.p_image_s}" height="95"></td>
								<td><a href="/shop/detail?shop_idx=${k.shop_idx}">${k.p_name}</a></td>
								<td>할인가 : <fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0" />원 <br>
								<font color="red">(할인률 : ${k.getPercent()}%)</font></td>
								<td>시중가 : <fmt:formatNumber value="${k.p_price}" pattern="#,##0"/>원</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</tbody>

	</table>
</body>
</html>