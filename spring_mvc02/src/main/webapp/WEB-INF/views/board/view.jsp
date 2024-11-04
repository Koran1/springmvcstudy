<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - View</title>
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
	#bbs img {
		width: 150px;
		height: 150px;
	}
	input{
		margin: 5px;
		padding: 5px;
	}
</style>
</head>
<body>
	<div id="bbs">

		<form method="post">
			<table summary="게시판 상세 보기">
			<caption><h2>Board 게시판 상세 보기</h2></caption>
				<tbody>
					<tr>
						<th bgcolor="#B2CCFF">작성자</th>
						<td>${boardvo.writer }</td>
					</tr>
					<tr>
						<th bgcolor="#B2CCFF">제목</th>
						<td>${boardvo.title }</td>
					</tr>
					<tr>
						<th bgcolor="#B2CCFF">날짜</th>
						<td>${boardvo.regdate }</td>
					</tr>
					<tr>
						<th bgcolor="#B2CCFF">첨부파일</th>
						<c:choose>
							<c:when test="${empty boardvo.f_name}">
								<td>첨부파일 없음</td>
							</c:when>
							<c:otherwise>
								<td><img alt="img" src="resources/upload/${boardvo.f_name}">
									<p>${boardvo.f_name }</p></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th bgcolor="#B2CCFF">내용</th>
						<td colspan="2"><textarea cols="90" rows="15">${boardvo.content }</textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="hidden" name="idx" value="${idx}">
							<input type="hidden" name="cPage" value="${cPage}"> 
							<input type="button" value="목록" onclick="location.href='/board_list?cPage=${cPage}'" />
							<input type="button" value="답글" onclick="ans_write(this.form)" />
							<input type="button" value="수정" onclick="board_update(this.form)" />
							<input type="button" value="삭제" onclick="board_delete(this.form)" />
							<input type="hidden" name="seq" value="" /> 
							<input type="hidden" name="pwd" />
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function ans_write(f) {
			f.action="/ans_write";
			f.submit();
		}
		function board_update(f) {
			f.action="/board_update";
			f.submit();
		}
		function board_delete(f) {
			f.action="/board_delete";
			f.submit();
		}
	</script>
	
</body>
</html>