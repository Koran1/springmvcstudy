<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	font-size: 12pt;
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
		<caption>
			<h2>:: 장바구니 내용 ::</h2>
		</caption>
		<tr bgcolor="#dedede">
			<th width="10%">제품번호</th>
			<th width="25%">제품명</th>
			<th width="25%">단가</th>
			<th width="15%">수량</th>
			<th width="15%">금액</th>
			<th width="10%">삭제</th>
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
						<td>${k.p_num}</td>
						<td>${k.p_name}</td>
						<td>정가 : <fmt:formatNumber value="${k.p_price}" pattern="#,##0"/> 원<br>
						<font color="red"> 세일가격 : <fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0" /> 원</font>
						</td>
						<td>
						<form action="/shop/cart_edit" method="post">
							<input type="number" name="p_su" min="1" value="${k.p_su}" style="width: 50px;">
							<input type="hidden" name="cart_idx" value="${k.cart_idx }">
							<input type="submit" value="수정">
						</form>
						
						</td>
						<td> <fmt:formatNumber value="${k.getTotalPrice()}" pattern="#,##0" /> 원</td>
						<td><button onclick="deleteCart(${k.cart_idx})">삭제</button></td>
					</tr>
					<c:set var="cartTotal" value="${cartTotal = cartTotal + (k.p_saleprice * k.p_su)}"></c:set>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="6" style="text-align: right; padding:10px 50px;">
			총 결재액 : <fmt:formatNumber value="${cartTotal}" pattern="#,##0"/> 원 </td>
		</tr>
	</table>
	<script type="text/javascript">
		function deleteCart(cart_idx){
			location.href = "/shop/cart_delete?cart_idx="+cart_idx;
		}
	</script>
</body>
</html>