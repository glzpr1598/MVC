<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			table, tr, th, td {
				border: thin solid black;
				border-collapse: collapse;
				padding: 5px;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>
				<td>${item.idx}</td>
				<td>${item.subject}</td>
				<td>${item.user_name}</td>
				<td>${item.reg_date}</td>
				<td>${item.bHit}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<script>
	</script>
</html>