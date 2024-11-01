<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - View</title>
<style type="text/css">
table{
	width: 800px;
	margin: 0px auto;
}
tr {
	    text-align:center;
	    padding:4px 10px;
	    background-color: #F6F6F6;
	}
	
th {
		width: 15%;
	    text-align:center;
	    padding:4px 10px;
	    background-color: #B2CCFF;
	}
	img{
		width: 150px;
		height: 150px;
	}
</style>
</head>
<body>
	<form method="post">
	<table width="700">
	<tbody>
	<tr>
		<th bgcolor="#B2EBF4">작성자</th>
		<td>${boardvo.writer }</td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">제목</th>
		<td>${boardvo.title }</td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">날짜</th>
		<td>${boardvo.regdate }</td>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">첨부파일</th>
		<c:choose>
			<c:when test="${empty boardvo.f_name}">
				<td>첨부파일 없음</td>
			</c:when>
			<c:otherwise>
				<td>
					<img alt="img" src="resources/upload/${boardvo.f_name}">
					<p>${boardvo.f_name }</p>
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<th bgcolor="#B2EBF4">내용</th>
		<td colspan="2"><textarea cols="90" rows="15">${boardvo.content }</textarea></td>
	</tr>
	</tbody>
	<tfoot>
	<tr>
     <td colspan="2">
        <input type="button" value="목록" onclick="location.href='/board_list'" />
        <input type="button" value="답글" onclick="" />
        <input type="button" value="수정" onclick="" />
        <input type="button" value="삭제" onclick="" />
        <input type="hidden" name="seq" value="" />
	    <input type="hidden" name="pwd" />
     </td>
	</tr>
	</tfoot>
	</table>
	</form>
</body>
</html>