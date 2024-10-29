<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBS - write</title>
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
	
	.note-btn-group{width: auto;}
	.note-toolbar{width: auto;}
</style>
<script type="text/javascript">
	<c:if test="${pwdchk == 'fail' }">
		alert("비밀번호 오류!");
	</c:if>
</script>
</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data" action="/bbs_update_ok">
		<table summary="게시판 수정하기">
			<caption>게시판 수정하기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="subject" value="${bvo.subject}" size="45" required></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" value="${bvo.writer}" size="12" required></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" cols="50" rows="8" id="content">${bvo.content}</textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" name="file_name">
						<c:choose>
							<c:when test="${empty bvo.f_name}">
								<b>첨부된 파일 없음</b>
								<input type="hidden" name="old_f_name" value="">
							</c:when>
							<c:otherwise>
								<p>${bvo.f_name}</p>
								<input type="hidden" name="old_f_name" value="${bvo.f_name}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12" required></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정하기">
						<input type="button" value="목록" onclick="bbs_list()">
						<input type="hidden" name="cPage" value="${cPage}">
						<input type="hidden" name="b_idx" value="${bvo.b_idx}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	
	<script type="text/javascript">
		function bbs_list(){
			location.href="/bbs";
		}
	</script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="resources/js/summernote-lite.js" ></script>
	<script src="resources/js/lang/summernote-ko-KR.js" ></script>
	<script type="text/javascript">
		$(function() {
			$("#content").summernote({
				lang : 'ko-KR',
				height : 300,
				focus : true,
				placeholder : "최대 3000자까지 쓸 수 있습니다.",
				callbacks : {
					onImageUpload : function(files, editor) {
						for (let i = 0; i < files.length; i++) {
							sendImage(files[i], editor);
						}
					}
				}
			});
		});
		
		function sendImage(file, editor) {
			// FormData 객체를 전송할 때 , jQuery가 설정
		  	let frm = new FormData();
		  	frm.append("s_file", file);
		  	$.ajax({
			 url : "/saveImg",
			 data : frm,
			 method : "post",
			 contentType : false,
			 processData : false,
			 cache : false,
			 dataType : "json",
			 success : function(data) {
				 const path = data.path;
				 const fname = data.fname ;
				 console.log(path, fname);
				 $("#content").summernote("editor.insertImage", path+"/"+fname);
			 },
			 error : function() {
				alert("읽기실패");
			 }
		  });
		}
	</script>
</body>
</html>

