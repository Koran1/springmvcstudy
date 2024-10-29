<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Form</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>비밀번호 받을 이메일을 넣어주세요</h2>
	<form action="/email_send" method="post">
		<input type="email" name="email"
            pattern="[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*" title="이메일 양식" required>
        <input type="submit" value="전송">
	</form>
	
	<form action="/email_send_ok" method="post" onsubmit="return authNum()">
		<input type="number" id="authNumber" name="authNumber" placeholder="인증번호 입력" maxlength="6" required>
		<input type="submit" id="chkbtn" value="확인" >
	</form>
	
	<c:if test="${!empty chk}">
		alert("일치합니다");
	</c:if>
	
	<script type="text/javascript">
		function authNum(){
			const num = document.querySelector("#authNumber").value;
			if(num.length !== 6 || isNaN(num)){
				alert("6자리 숫자만 입력해야 합니다");
				return false;
			}
			return true;
			
		}
	</script>
</body>
</html> 