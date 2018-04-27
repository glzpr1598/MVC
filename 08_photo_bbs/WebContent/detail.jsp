<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		table,th,td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px 10px;
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
		<c:if test="${info.newFileName != null}">
		<tr>
			<th>이미지</th>
			<td>
				<img src="upload/${info.newFileName}"/>
				<a href="upload/${info.newFileName}">link</a>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="2">
				<a href="./list">리스트 가기</a><!-- ./ 현재의 루트 -->
				&nbsp;&nbsp;
				<a href="./updateForm?idx=${info.idx}">수정</a>
				&nbsp;&nbsp;
				<a href="./del?idx=${info.idx}">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>






