<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			}
			table {
				width: 400px;
			}
			th {
				width: 70px;
			}
		</style>
	</head>
	<body>
		<form action="updateForm?idx=${ dto.idx }" method="post">
			<table>
				<tr>
					<th>글번호</th>
					<td>${ dto.idx }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${ dto.subject }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${ dto.user_name }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${ dto.reg_date }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${ dto.bHit }</td>
				</tr>
				<tr>
					<td colspan="2">${ dto.content }</td>
				</tr>
			</table>
			<input type="button" id="btnList" value="목록"/>
			<input type="submit" value="수정"/>
		</form>
	</body>
	<script>
		// 목록
		$("#btnList").click(function() {
			location.href="main.html";
		});
	</script>
</html>