<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script type="text/javascript">
	<c:if test="${loginchk == '1'}">
	alert("로그인 성공!");
	</c:if>
</script>
</head>
<body>
	<h1>First Spring MVC</h1>
	<h1>Non-Annotation</h1>
	<h2><a href="/start1">start1</a></h2>
	<h2><a href="/start2">start2</a></h2>
	<hr>
	<h1>Annotation</h1>
	<h2><a href="/start3">start3</a></h2>
	<form action="/start4" method="post">
		<button type="submit">start4-전송1</button>
		<input type="submit" value="start4-전송2">
	</form>
	<h2><a href="/start5">start5</a></h2>
	<h2><a href="/start6">start6</a></h2>
	<h2><a href="/start7">start7</a></h2>
	<hr>
	
	<h2>서버에게 정보 보내기</h2>
    <form action="/start8" method="get" autocomplete="on">
        <p>
           <label> 이름 : <input type="text" name="username" placeholder="이름 입력" required> </label></p>
        <p>   
           <label> 나이 : <input type="number" name="userage" placeholder="나이 입력" required> </label>
        </p>
        <input type="submit" value="서버에게 보내기">
        <input type="reset" value="취소">
    </form>
    <br>
    <hr>
    <form action="/calc" method="post">
    	<p> 수1 : <input type="number" size="15" name="s1" required>
    	<p> 수2 : <input type="number" size="15" name="s2" required>
    	<p> 연산자 :
    		<input type="radio" name="op" value="+" checked>+
    		<input type="radio" name="op" value="-">-
    		<input type="radio" name="op" value="*">*
    		<input type="radio" name="op" value="/">/
    	</p>
    	<p> 취미 : 
    		<input type="checkbox" name="hobby" value="축구"> 축구
    		<input type="checkbox" name="hobby" value="야구"> 야구
    		<input type="checkbox" name="hobby" value="농구"> 농구
    		<input type="checkbox" name="hobby" value="배구"> 배구
    	</p>
    	
    	<input type="hidden" name="cPage" value="2">
    	<input type="submit" value="보내기">
    </form> 
    
    <br>
    <hr>
    <form action="/calc2" method="post">
    	<p> 수1 : <input type="number" size="15" name="s1" required>
    	<p> 수2 : <input type="number" size="15" name="s2" required>
    	<p> 연산자 :
    		<input type="radio" name="op" value="+" checked>+
    		<input type="radio" name="op" value="-">-
    		<input type="radio" name="op" value="*">*
    		<input type="radio" name="op" value="/">/
    	</p>
    	<p> 취미 : 
    		<input type="checkbox" name="hobby" value="축구"> 축구
    		<input type="checkbox" name="hobby" value="야구"> 야구
    		<input type="checkbox" name="hobby" value="농구"> 농구
    		<input type="checkbox" name="hobby" value="배구"> 배구
    	</p>
    	
    	<input type="hidden" name="cPage" value="2">
    	<input type="submit" value="보내기">
    </form> 
    <hr>
    <h3><a href="/member_list">Members 전체 보기</a></h3>
    <hr>
    
    <!-- 이미지는 무조건 webapp-resources 안에 넣어야 한다 -->
    <h3>이미지 보기</h3>
    <!-- img 보는 4가지 방법 -->
    <img alt="img01" src="/resources/images/img01.jpg" style="width: 100px;"><br>
    <img alt="img01" src="resources/images/img01.jpg" style="width: 100px;"><br>
    <%-- <c:url> 을 사용하는 게 더 안정적이다
    	 => 어플리케이션 컨텍스트 경로가 변경되어도 이미지 경로를 올바르게 참조한다
    	 => 프로젝트 여러 개를 tomcat을 올리는 경우에 유용 --%>
    <img alt="img01" src="<c:url value='/resources/images/img01.jpg'></c:url>" style="width: 100px;"><br>
    <img alt="img01" src="<c:url value='resources/images/img01.jpg'></c:url>" style="width: 100px;"><br>
    <hr>
    
    <!-- cos 라이브러리 받아서 사용 -->
    <h3>파일 업로드-1</h3>
    <form action="/f_up1" method="post" enctype="multipart/form-data">
    	<p>올린 사람 : <input type="text" name="name"></p>
    	<p>파일 : <input type="file" name="f_name"></p>
    	<p><input type="submit" value="파일 업로드"></p>
    </form>
    
    <!-- commons-fileupload, commons-io 라이브러리 pom.xml에 등록
    	 servlet-context.xml 에서 파일 업로드용 클래스 등록
     -->
    <h3>파일 업로드-2</h3>
    <form action="/f_up2" method="post" enctype="multipart/form-data">
    	<p>올린 사람 : <input type="text" name="name"></p>
    	<p>파일 : <input type="file" name="f_name"></p>
    	<p><input type="submit" value="파일 업로드"></p>
    </form>
    
    <h3>파일 업로드-3</h3>
    <form action="/f_up3" method="get" enctype="multipart/form-data">
    	<p>올린 사람 : <input type="text" name="name"></p>
    	<p>파일 : <input type="file" name="f_name"></p>
    	<p><input type="submit" value="파일 업로드(get 에러날 예정)"></p>
    </form>
    <hr>
    <!-- VO를 이용해서 파라미터 처리 -->
    <h3>파일 업로드-4</h3>
    <form action="/f_up4" method="post" enctype="multipart/form-data">
    	<p>올린 사람 : <input type="text" name="name"></p>
    	<p>파일 : <input type="file" name="f_name"></p>
    	<p><input type="submit" value="파일 업로드"></p>
    </form>
    <hr>
    <hr>
    <h2><a href="/go_ajax_xml">Ajax xml 연습하는 장소이동</a></h2>
    <h2><a href="/go_ajax_json">Ajax json 연습하는 장소이동</a></h2>
    <h2><a href="/go_ajax_DB">Ajax DB 연습하는 장소이동</a></h2>
    <h2><a href="/emp">동적 쿼리 연습하는 장소이동</a></h2>
    <h2><a href="/email">이메일 연습하는 장소이동</a></h2>
    <h2><a href="/login_form">로그인 연습하는 장소이동</a></h2>
    
</body>
</html>