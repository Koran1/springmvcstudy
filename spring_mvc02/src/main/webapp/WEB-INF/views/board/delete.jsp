<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - delete</title>
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
		width:120px;
	    text-align:center;
	    padding:4px 10px;
	    background-color: #B2CCFF;
	}
</style>
</head>
<script type="text/javascript">
	<c:if test="${pwdchk == '1' }">
		alert("비밀번호 오류!");
	</c:if>
</script>
<body>
	<form method="post">
		<table summary="게시글 삭제">
		<caption><h2>게시글 삭제</h2></caption>
		<tbody>
			<tr>
				<th>비밀번호</th>
				<td align="left"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" value="${cPage}" name="cPage">
					<input type="hidden" value="${idx}" name="idx">
					<input type="button" value="삭제" onclick="board_delete_ok(this.form)" /> 
					<input type="reset" value="취소" />
					<input type="button" value="목록" onclick="board_list_go(this.form)" /> 
				</td>
			</tr>
            </tbody>
		</table>
	</form>
	<script type="text/javascript">
	function board_delete_ok(f) {
		f.action="/board_delete_ok";
		f.submit();
	}
	function board_list_go(f) {
		f.action="/board_list?cPage=${cPage}";
		f.submit();
	}
</script>
</body>
</html>