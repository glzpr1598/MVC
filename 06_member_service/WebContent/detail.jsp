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
		text-align: center;
	}
</style>
</head>
<body>
	<jsp:include page="loginBox.jsp"/>
	<table>
		<tr>
			<th>ID</th>
			<td>${info.id}</td>
		</tr>
		<tr>
			<th>PW</th>
			<td>${info.pw}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${info.name}</td>
		</tr>
		<tr>
			<th>Age</th>
			<td>${info.age}</td>
		</tr>
		<tr>
			<th>Gender</th>
			<td>${info.gender}</td>
		</tr>
		<tr>
			<th>E-mail</th>
			<td>${info.email}</td>
		</tr>
		<tr>
			<td colspan="2">
				<button onclick="location.href='updateForm?id=${info.id}';">수정</button>
			</td>
		</tr>
	</table>
	<button onclick="location.href='main';">리스트</button>
</body>
<script>
	var msg = "${msg}";
	if (msg != "") {
		alert(msg);
	}
</script>
</html>