<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS - detail</title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	#bbs table {
	    width:800px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
		width: 12%;
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
</style>

</head>
<body>
	<div id="bbs">
		<form method="post">
		<table summary="게시판 상세보기">
			<caption>게시판 상세보기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${bvo.subject }</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bvo.writer }</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre id="content">${bvo.content }</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bvo.f_name}">
							<td>첨부파일 없음</td>
						</c:when>
						<c:otherwise>
							<td><a href="/board_down?f_name=${bvo.f_name}">
							<img src="resources/upload/${bvo.f_name}" style="width: 80px;"><br>
							${bvo.f_name}</a></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="b_idx" value="${bvo.b_idx }">
						<input type="hidden" name="cPage" value="${cPage}">
						<input type="button" value="수정" onclick="bbs_update(this.form)">
						<input type="button" value="삭제" onclick="bbs_delete(this.form)">
						<input type="button" value="목록" onclick="bbs_list(this.form)">
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	
	<!-- 댓글 입력 창 -->
	<div style="padding: 10px; width: 500px; margin: 0 auto;">
		<form action="/comment_insert" method="post">
			<fieldset>
				<p> 이름 : <input type="text" name="writer"></p>
				<p> 내용 : <textarea rows="3" cols="40" name="content"></textarea>
				<input type="hidden" name="b_idx" value="${bvo.b_idx}">
				<input type="hidden" name="cPage" value="${cPage}">
				<input type="submit" value="댓글 저장하기">
			</fieldset>
		</form>
	
	</div>
	
	<!-- 댓글 출력 창 -->
	<div style="display: table; margin: 0 auto;">
		<c:forEach var="k" items="${clist}">
			<div style="border: 1px solid #cc00cc; width: 500px; margin: 5px; padding: 5px;">
				<form action="/comment_delete" method="post">
					<p> 이름 : ${k.writer} </p>
					<pre> 내용 : ${k.content} </pre>
					<p> 날짜 : ${k.write_date} </p>
					<%-- 삭제 버튼은 관리자 or 작성자만 --%>
					<%-- db 삭제 후 새로고침 --%>
					<input type="submit" value="댓글 삭제">
					<input type="hidden" name="b_idx" value="${k.b_idx}">
					<input type="hidden" name="c_idx" value="${k.c_idx}">
					<input type="hidden" name="cPage" value="${cPage}">
				</form>
			</div>
		</c:forEach>
	</div>
	
	<script type="text/javascript">
		function bbs_update(f){
			f.action = "/bbs_update";
			f.submit();
		}
		function bbs_delete(f){
			f.action = "/bbs_delete";
			f.submit();
		}
		function bbs_list(f){
			f.action = "/bbs";
			f.submit();
		}
	</script>
	
	
</body>
</html>

