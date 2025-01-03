<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - Answer write</title>
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
	input{
		margin: 5px;
		padding: 5px;
	}
</style>
</head>
<body>
	
	<form method="post" enctype="multipart/form-data">
		<table summary="게시판 답글 쓰기">
			<caption><h2>Board 게시판 답글 쓰기</h2></caption>
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
					<input type="hidden" name="idx" value="${idx}">
					<input type="hidden" name="cPage" value="${cPage}"> 
					
					<input type="button" value="답글 입력" onclick="board_ans_write_ok(this.form)" /> 
					<input type="reset" value="취소" />
					<input type="button" value="목록" onclick="board_list_go(this.form)" /> 
				</td>
			</tr>
            </tbody>
		</table>
	</form>
	<script type="text/javascript">
	function board_ans_write_ok(f) {
		for (let i = 0; i < f.elements.length; i++) {
			if(f.elements[i].value==""){
				if(i == 3) continue;
				alert(f.elements[i].name + "를 입력하세요");
				f.elements[i].focus();
				return;
			}
		}
		f.action="/board_ans_write_ok";
		f.submit();
	}
	function board_list_go(f) {
		f.action="/board_list";
		f.submit();
	}
</script>
</body>
</html>