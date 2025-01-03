<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	padding: 10px;
}
</style>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<table>
		<tr>
			<td width="40%">제품Category</td>
			<td width="60%">${shopvo.category}</td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${shopvo.p_num}</td>
		</tr>
		<tr>
			<td width="40%">제품이름</td>
			<td width="60%">${shopvo.p_name}</td>
		</tr>
		<tr>
			<td width="40%">제품판매사</td>
			<td width="60%">${shopvo.p_company}</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%"> <fmt:formatNumber value="${shopvo.p_price}" pattern="#,##0" /><br>
			<font style="color: red">(할인가 : <fmt:formatNumber value="${shopvo.p_saleprice}" pattern="#,##0" />)</font></td>
		</tr>
		<tr>
			<td colspan="2">제품설명</td>
		</tr>
		<tr>
			<td colspan="2">${shopvo.p_content}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<img src="resources/images/${shopvo.p_image_l}"></td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" value="장바구니에 담기" onclick="add_cart()"> 
			<input type="button" value="장바구니 보기" onclick="show_cart()">
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
		function add_cart() {
			// 해당 상품의 pk 를 가지고 와야 한다
			const shop_idx = "${shopvo.shop_idx}";
			location.href = '/shop/addCart?shop_idx='+shop_idx;
		}
		function show_cart() {
			location.href = '/shop/showCart';
		}
	</script>
</body>
</html>