<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<table>
		<tr>
			<th>작성자</th>
			<td>${info.user_name}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${info.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${info.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="./">리스트 가기</a>
				&nbsp;&nbsp;
				<a href="./updateForm?idx=${info.idx}">수정</a>
				&nbsp;&nbsp;
				<a href="./del?idx=${info.idx}">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>