<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
</head>
<style>
	table, tr, th, td {
		
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
		text-align: center;
	}
	table {
	    height: 500px;
	    width: 500px;
	}
	input[type=text] {
		width: 100%;
	}
</style>
<body>
	<form action="write" method="post">
	    <table>
	        <tr>
	            <th>제목</th>
	            <td><input name="subject" type="text"/></td>
	        </tr>
	        <tr>
	            <th colspan="2">내용</th>
	        </tr>
	        <tr>
	            <th colspan="2"><textarea name="content" rows="30" cols="80" ></textarea></th>
	        </tr>
	        <tr>
	            <th colspan="2"><input type="submit" value="완료"></th>
	        </tr>
	    </table>
	</form>
</body>
<script>
	if (${msg} != "") {
		alert("${msg}");
	}
</script>
</html>