<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax - DB</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
	span{width: 150px; color: red;}
	input{border: 1px solid blue;}
	table{width: 80%; border-collapse: collapse; margin: 0px auto;}
	table, th, td{border: 1px solid gray; text-align: center;}
	h2{text-align: center;}
</style>
</head>
<body>
	<h2>Ajax 를 활용한 DB 처리</h2>
	<h2>회원 정보 입력하기</h2>
	<form method="post" id="myForm">
		<table>
			<thead>
				<tr><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th> </tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="text" size="14" name="m_id" id="m_id"><br><span>중복여부</span>
					</td>
					<td>
						<input type="password" size="10" name="m_pw" id="m_pw">
					</td>
					<td>
						<input type="text" size="10" name="m_name" id="m_name">
					</td>
					<td>
						<input type="number" size="10" name="m_age" id="m_age">
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="가입하기" id="btn_join" disabled>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	<br><br><br><br>
	<h2>회원 정보 보기</h2>
	<div>
		<table id="list">
			<thead>
				<tr><th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th><th>가입일</th><th>삭제</th></tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		// Ajax 이용해서 db 정보 가져오기
		// 1. xml
		function getList(){
			$.ajax({
				url : "/ajax_db_list",
				method : "post",
				dataType : "xml",
				success : function(data) {
					let tbody = "";
					$(data).find("member").each(function(i, v){
					// $.each((data).find("member"), function(idx, obj){		=> $(this) 대신에 obj 쓰면 되긴함
						tbody += "<tr>";
						// tbody += "<td>" + $(this).find("m_idx").text() +"</td>"
						tbody += "<td>" + (i+1) +"</td>"
						tbody += "<td>" + $(this).find("m_id").text() +"</td>"
						tbody += "<td>" + $(this).find("m_pw").text() +"</td>"
						tbody += "<td>" + $(this).find("m_name").text() +"</td>"
						tbody += "<td>" + $(this).find("m_age").text() +"</td>"
						tbody += "<td>" + $(this).find("m_reg").text() +"</td>"
						tbody += "<td><input type='button' value='삭제' id='member_del' name='"+$(this).find("m_idx").text()+"'></td>"
						tbody += "</tr>"
					})
					$("#tbody").append(tbody);
				},
				error : function() {
					alert("가져오기가 실패했슈");
				}
			});
		}
		
		// 2. json
		// spring 에서는 json을 만들거나 파싱할 때 여러 가지 라이브러리를 사용할 수 있다
		// => gson 라는 라이브러리를 사용하자 (pom.xml에 등록)
		function getList2(){
			$.ajax({
				url : "/ajax_db_list2",
				method : "post",
				dataType : "json",
				success : function(data) {
					let tbody = "";
					$.each(data, function(index, obj){
						tbody += "<tr>";
						tbody += "<td>" + obj.m_idx +"</td>"
						tbody += "<td>" + obj.m_id +"</td>"
						tbody += "<td>" + obj.m_pw +"</td>"
						tbody += "<td>" + obj.m_name +" 님</td>"
						tbody += "<td>" + obj.m_age +"살</td>"
						tbody += "<td>" + obj.m_reg +"</td>"
						tbody += "<td>" +"삭제?" +"</td>"
						tbody += "</tr>"
					});
					$("#tbody").append(tbody);
				},
				error : function() {
					alert("가져오기가 실패했슈2");
				}
			});
		}
		// 3. csv
		function getList3(){
			$.ajax({
				url : "/ajax_db_list3",
				method : "post",
				dataType : "text",
				success : function(data) {
					let rows = data.split("\n");
					let tbody = "";
					$.each(rows, function(index, row){
						// 헤더가 있으면 제외
						if(index === 0 || row.trim() === "") return true;
						
						tbody +="<tr>"
						let cols = row.split(",");
						
						$.each(cols, function(i, col){
							tbody +="<td>" + col + "</td>";
						})
						tbody += "<td>삭제</td>"
						tbody += "</tr>"
					})
					$("#tbody").append(tbody);
				},
				error : function() {
					alert("가져오기가 실패했슈3");
				}
			});
		}
		
		// 입력 체크용
		let isInputChk = false;
		
		function toggleJoinButton() {
			const passwordField = $("#m_pw").val() !== "";
			const nameField = $("#m_name").val() !== "";
			const ageField = $("#m_age").val() !== "" && parseInt($("#m_age").val()) > 0;
			if(isInputChk && passwordField && nameField && ageField){
				$("#btn_join").prop("disabled", false);
			}else{
				$("#btn_join").prop("disabled", true);
			}
		}
		
		// 아이디 중복 여부 확인
		$("#m_id").keyup(function(){
			$.ajax({
				url : "/ajax_db_chkid",
				method : "post",
				data : "m_id="+$("#m_id").val(),			// 파라미터 1개일 때 사용
				dataType : "text",
				success : function(data){
					if(data == '0'){
						$("span").text("사용가능");
						$("span").css("color", "blue");
						isInputChk = true;
						is
					}else{
						$("span").text("사용불가");
						$("span").css("color", "red");
						isInputChk = false;
						
					}
					toggleJoinButton();
				},
				error : function(){
					alert("아이디 읽기 실패")
				}
			});
		});
		
		$("#m_pw, #m_name").keyup(toggleJoinButton);
		$("#m_age").on("change keyup", toggleJoinButton);
		
		// 회원정보 insert
		// 파라미터 여러 개는 직렬화 serialize() => form 태그 안에서 보통 사용
		$("#btn_join").click(function () {
			let param = $("#myForm").serialize();
			$.ajax({
				url : "/ajax_member_join",
				method : "post",
				// data : param,
				data : { m_id : $("#m_id").val(), 
						 m_pw : $("#m_pw").val(),
						 m_name : $("#m_name").val(),
						 m_age : $("#m_age").val()
						},
				dataType : "text",
				success : function (data) {
					if(data == '0'){
						alert("가입실패");
					}else{
						$("#tbody").empty();
						getList();
						
						// 가입 창 초기화
						$("m_id").val("");
						$("m_pw").val("");
						$("m_name").val("");
						$("m_age").val("");
						
						// form 은 배열로 넘어온다!
						$("#myForm")[0].reset();
						$("span").text("중복여부");
						$("span").css("color", "red");
						$("#btn_join").prop("disabled", true);
					}
				},
				error : function () {
					alert("읽기 실패");
				}
				
			});
		});
		
		// 동적쿼리 (동적 바인딩 변수 이므로 click 안됨 => on 사용)
		// on "click" - 동적 / click - 최초에 선언된 element에만 동작
		// #list = 부모 / member_del = 자식
		$("#list").on("click", "#member_del", function(){
			// alert($(this).attr("name"));
			if(confirm("정말 삭제할까요?")){
				$.ajax({
					url : "/ajax_member_delete",
					method : "post",
					data : "m_idx="+ $(this).attr("name"),
					dataType : "text",
					success : function(data) {
						if(data == '0'){
							alert("삭제 실패");
						} else if(data == '1'){
							$("#tbody").empty();
							getList();
						}
					},
					error : function() {
						alert("읽기 실패3");
					}
				});
			} else{
				alert("삭제가 취소 되었습니다");
			}
		});
		
		
		
		
		getList();
	</script>
	
</body>
</html>