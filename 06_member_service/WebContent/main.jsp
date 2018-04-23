<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 라이브러리 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table, tr, th, td {
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
	}
</style>
</head>
<body>
	<jsp:include page="loginBox.jsp"/>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>삭제</th>
		</tr>
		<!-- JSTL을 이용해 멤버 list 출력 -->
		<c:forEach var="member" items="${list}">
			<tr>
				<td><a href="memberDetail?id=${member.id}">${member.id}</a></td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td><a href="memberDel?id=${member.id}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
	var msg = "${msg}";
	if (msg != "") {
		alert(msg);
		
		location.href="main"; // 삭제 후 파라미터가 남아있는 것을 막기 위해(forward로 보내면 URL이 바뀌지 않기 때문)
	}
</script>
</html>