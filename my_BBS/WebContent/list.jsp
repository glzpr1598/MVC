<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
<style>
	table, th, tr, td {
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
		text-align: center;
	}
	.thTitle {
		width: 300px;
		text-align: left;
	}
	
	a:link {
		color: black;
		text-decoration: none;
	}
	
	a:hover {
		text-decoration: underline;
	}
	
	a:visited {
		color: gray;
	}
</style>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="list" items="${ list }">
		<tr>
			<td>${ list.bId }</td>
			<td  class="thTitle"><a href="detail.do?bId=${ list.bId }">${ list.bTitle }</a></td>
			<td>${ list.bName }</td>
			<td>${ list.bDate }</td>
			<td>${ list.bHit }</td>
		</tr>
		</c:forEach> 
	</table>
	<button id="btnWrite">글쓰기</button>
</body>
<script>
	$("#btnWrite").on("click", function() {
		location.href="writeForm.do";
	});
</script>
</html>