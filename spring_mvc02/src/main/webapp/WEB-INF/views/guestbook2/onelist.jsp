<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Onelist 2</title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
</style>
</head>
<body>
	<div>
		<h2>방명록 2 : 내용화면</h2>
		<hr>
		<p><a href="/guestbook2">
		[목록으로 이동]</a></p>
		<table>
			<tr align="center">
				<td bgcolor="#99ccff">작성번호</td>
				<td>${onelist.gb2_idx}</td>
			</tr>
			<tr align="center">
				<td bgcolor="#99ccff">작성자</td>
				<td>${onelist.gb2_name}</td>
			</tr>
			<tr align="center">
				<td bgcolor="#99ccff">제  목</td>
				<td>${onelist.gb2_subject}</td>
			</tr>
			<tr align="center">
				<td bgcolor="#99ccff">email</td>
				<td>${onelist.gb2_email}</td>
			</tr>
			<tr align="center">
				<td bgcolor="#99ccff">첨부파일</td>
				<c:choose>
					<c:when test="${empty onelist.gb2_f_name}">
					<td><b>첨부 파일 없음</b></td>
					</c:when>
					<c:otherwise>
						<td>
						<a href="/gb2_down?f_name=${onelist.gb2_f_name}">
						<img alt="" src="resources/upload/${onelist.gb2_f_name}" style="width: 200px">
						</a></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr align="center">
				<td colspan="2">
					<pre style="padding-left: 15px">${onelist.gb2_content}</pre>
				</td>
			</tr>
			<tfoot>
				<tr align="center">
					<td colspan="2">
						<form method="post">
						    <input type="hidden" name="gb2_idx" value="${onelist.gb2_idx}">
						    <input type="button" value="수정" onclick="update_go(this.form)">
						    <input type="button" value="삭제" onclick="delete_go(this.form)">
						</form>
						
						<script type="text/javascript">
							function update_go(f) {
								f.action="/gb2_update";
								f.submit();
							}
							function delete_go(f) {
								f.action="/gb2_delete";
								f.submit();
							}
						</script>	
					</td>
				</tr>
			</tfoot>
		</table>
		</form>
	</div>
</body>
</html>