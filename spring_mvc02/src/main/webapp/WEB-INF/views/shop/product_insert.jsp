<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Insert</title>
<style type="text/css">
	table{
		width: 600px;
		margin: 0 auto;
		border-collapse: collapse;
	}
	table, td{
		border: 1px solid black;
	}
	td{
		padding: 5px 10px;
	}
	input{
		padding: 5px;
		margin: 5px;
	}
</style>
</head>
<body>
	<jsp:include page="top.jsp" />
	<h1>상품등록 </h1>
	<form action="/shop/product_insert" method="post" encType="multipart/form-data">
		<table>
			<tr><td>분류</td>
			<td> <input type="radio" name="category" value="com001" checked> 컴퓨터
				<input type="radio" name="category" value="ele002"> 가전제품
				<input type="radio" name="category" value="sp003"> 스포츠
			</td></tr>
			<tr><td>제품번호</td><td> <input type="text" name="p_num" required> </td></tr>
			<tr><td>제품명</td><td> <input type="text" name="p_name" required> </td></tr>
			<tr><td>판매사</td><td> <input type="text" name="p_company" required> </td></tr>
			<tr><td>상품가격</td><td> <input type="number" min="0" name="p_price" required> </td></tr>
			<tr><td>할인가</td><td> <input type="number" min="0" name="p_saleprice" required> </td></tr>
			<tr><td>상품이미지s</td><td> <input type="file" name="p_image_s_file" required> </td></tr>
			<tr><td>상품이미지L</td><td> <input type="file" name="p_image_l_file" required> </td></tr>
			<tr><td colspan="2">상품내용</td></tr>
			<tr><td colspan="2">
				<textarea rows="8" cols="60" name="p_content"></textarea>
			</td></tr>
			<tr><td colspan="2" align="left"><input type="submit" value="상품등록"></tr>
		</table>
	</form>
</body>
</html>