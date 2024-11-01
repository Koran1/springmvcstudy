<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board - List</title>
<style type="text/css">
tr {
	text-align: center;
	padding: 4px 10px;
	background-color: #F6F6F6;
}

th {
	text-align: center;
	padding: 4px 10px;
	background-color: #B2CCFF;
}
</style>
</head>
<body>
	<h2>BBS 리스트</h2>
	<div id="bbs" align="center">
		<table summary="게시판 목록">
			<caption>Board 게시판 목록</caption>
			<thead>
				<tr class="title">
					<th class="no">번호</th>
					<th class="subject">제목</th>
					<th class="writer">글쓴이</th>
					<th class="reg">날짜</th>
					<th class="hit">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">해당 자료가 없습니다</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${list}" varStatus="c">
							<tr>
								<!-- count 는 1부터 시작 / index 는 0부터 시작 -->
								<td>${paging.totalRecord - ((paging.nowPage-1)*paging.numPerPage + c.index)}</td>

								<c:choose>
									<c:when test="${k.active == 1}">
										<td colspan="4"><span style="color: lightgray">삭제된
												게시물 입니다</span></td>
									</c:when>
									<c:otherwise>
										<td><a href="/board_detail?idx=${k.idx}&cPage=${paging.nowPage}">${k.title}</a></td>
										<td>${k.writer}</td>
										<td>${k.regdate}</td>
										<td>${k.hit}</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<ol class="paging">
							<!-- 이전 -->
							<c:choose>
								<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
									<li class="disable">이전으로</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="/bbs?cPage=${paging.beginBlock-paging.pagePerBlock}">이전으로</a></li>
								</c:otherwise>
							</c:choose>

							<!-- 블록안에 들어간 페이지번호들 -->
							<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}"
								step="1" var="k">
								<!-- 현재 페이지와 현재 페이지가 아닌 것을 구분하자 -->
								<c:if test="${k == paging.nowPage}">
									<li class="now">${k}</li>
								</c:if>
								<c:if test="${k != paging.nowPage}">
									<li><a href="/bbs?cPage=${k}">${k}</a></li>
								</c:if>
							</c:forEach>

							<!-- 다음 -->
							<c:choose>
								<c:when test="${paging.endBlock >= paging.totalPage}">
									<li class="disable">다음으로</li>
								</c:when>
								<c:otherwise>
									<%-- <li><a href="/bbs?cPage=${paging.beginBlock+paging.pagePerBlock}">다음으로</a></li> --%>
									<li><a href="/bbs?cPage=${paging.endBlock+1}">다음으로</a></li>
								</c:otherwise>
							</c:choose>
						</ol>
					</td>
					<td><input type="button" value="글쓰기"
						onclick="location.href = '/board_write'"></td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>