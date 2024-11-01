<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - write</title>
<style type="text/css">
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
<body>
	
	<form action="/board_write_ok" method="post" enctype="multipart/form-data">
		<table width="700px">
		<tbody>
			<tr>
				<th>작성자</th>
				<td align="left"><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td align="left"> <input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td align="left"><textarea rows="10" cols="60" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td align="left"><input type="file" name="file_name"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td align="left"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="입력" onclick="board_write_ok(this.form)" /> 
				<input type="reset" value="취소" />
				</td>
			</tr>
            </tbody>
		</table>
	</form>
	<script type="text/javascript">
	function board_write_ok(f) {
		for (let i = 0; i < f.elements.length; i++) {
			if(f.elements[i].value==""){
				if(i == 3) continue;
				alert(f.elements[i].name + "를 입력하세요");
				f.elements[i].focus();
				return;
			}
		}
		f.submit();
	}
</script>
</body>
</html>