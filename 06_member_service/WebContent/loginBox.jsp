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
	<div id="login"></div>
</body>
<script>
	var id = "${sessionScope.id}"
	
	if (id == "") {
		alert("로그인이 필요합니다.");
		location.href="index.jsp";
	} else {
		var content = id + "님, 안녕하세요.";
		content += " <a href='logout'>[로그아웃]</a>";
		document.getElementById("login").innerHTML = content;
	}
</script>
</html>