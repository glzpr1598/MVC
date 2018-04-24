<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	table,tr,th,td{
		border :2px solid black;
		border-collapse: collapse;
		text-align: center;
		padding: 10px 15px;
}
</style>
</head>
<body>
	<jsp:include page="loginBox.jsp"/>
	<br/>
	<form action="" method="post">
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
			
		<c:forEach items="${list}" var="bbs">
			<tr>
				<td>${bbs.idx}</td>
				<td>${bbs.user_name}</td>
				<td><a href="detail?idx=${bbs.idx}">${bbs.subject}</a></td>
				<td>${bbs.bHit}</td>
				<td>${bbs.REG_DATE}</td>
			</tr>
		</c:forEach>
		
		</table>
	</form>
	<button id="btnWrite" onclick="location.href='write.jsp'">글쓰기</button>
</body>
</html>