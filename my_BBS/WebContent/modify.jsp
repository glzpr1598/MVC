<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정</title>
<style>
	table, tr, th, td {
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
		text-align: center;
	}
	table {
		width: 400px;
	}
	input[type="text"] {
		width: 98%;
	}
	textarea {
		width: 98%;
	}
</style>
</head>
<body>
	<form action="modify.do" method="post">
		<input type="hidden" name="bId" value="${ dto.bId }"/>
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="bName" value="${ dto.bName }"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="bTitle" value="${ dto.bTitle }"/></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="bContent" rows="20">${ dto.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"/></td>
			</tr>
		</table>
	</form>
</body>
</html>