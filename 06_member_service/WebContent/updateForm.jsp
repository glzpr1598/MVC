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
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<jsp:include page="loginBox.jsp"/>
	<form action="memberUpdate" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td>
					${info.id}
					<input type="hidden" name="id" value="${info.id}"/>
				</td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input name="pw" type="password" value="${info.pw}"/></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><input name="name" value="${info.name}"/></td>
			</tr>
			<tr>
				<th>Age</th>
				<td><input name="age" value="${info.age}"/></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td>
					<input id="radioMale" type="radio" name="gender" value="남"/>남성
		        	<input id="radioFemale" type="radio" name="gender" value="여"/>여성
				</td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input name="email" value="${info.email}"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<button  onclick="location.href='updateForm?id=${info.id}';">수정</button>
				</td>
			</tr>
		</table>
	</form>
	<button onclick="location.href='main';">리스트</button>
</body>
<script>
	var msg = "${msg}";
	if (msg != "") {
		alert(msg);
	}
	
	// 남, 여 체크
	var radioMale = $("#radioMale");
	var radioFemale = $("#radioFemale");
	if ("${info.gender}" == "남 ") {
		radioMale[0].checked = true;
	} else {
		radioFemale[0].checked = true;
	}
	
</script>
</html>